package com.mycompany.sistemaplanejago.componentes.graficoLinhaHome;

import com.mycompany.sistemaplanejago.controller.LancamentoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineChartPanel extends JPanel {

    private LancamentoController lancamentoController;
    private List<Line> dataLines;

    private static final int PADDING = 40;
    private static final int LABEL_PADDING = 25; 
    private static final int POINT_RADIUS = 4; 
    private static final int HIT_RADIUS = 8; 
    private static final int TEXT_OFFSET = 5;

    // Variáveis de estado para o tooltip de hover
    private LineData hoveredPoint = null;
    private String hoveredValueText = null;
    private int hoveredX = -1;
    private int hoveredY = -1;

    public LineChartPanel() {
        this.lancamentoController = new LancamentoController();
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(229, 229, 246)); 

        // detecta o hover sobre os pontos
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                checkHover(e.getPoint());
            }

            public void mouseExited(MouseEvent e) {
                // reseta o estado do hover e redesenha
                if (hoveredPoint != null) {
                    hoveredPoint = null;
                    hoveredValueText = null;
                    hoveredX = -1;
                    hoveredY = -1;
                    repaint();
                }
            }
        });
    }

    public void loadData() {
        dataLines = new ArrayList<>(); 
        
        hoveredPoint = null;
        hoveredValueText = null;
        hoveredX = -1;
        hoveredY = -1;

        try {
            List<Map.Entry<String, BigDecimal>> receitasPorMesEntry = lancamentoController.getReceitasParaGraficoPorMesSemestreAtualComNome();
            List<Map.Entry<String, BigDecimal>> despesasPorMesEntry = lancamentoController.getDespesasParaGraficoPorMesSemestreAtualComNome();

            List<LineData> receitasDataPoints = new ArrayList<>();
            for (Map.Entry<String, BigDecimal> entry : receitasPorMesEntry) {
                receitasDataPoints.add(new LineData(entry.getKey(), entry.getValue()));
            }

            List<LineData> despesasDataPoints = new ArrayList<>();
            for (Map.Entry<String, BigDecimal> entry : despesasPorMesEntry) {
                despesasDataPoints.add(new LineData(entry.getKey(), entry.getValue()));
            }

            dataLines.add(new Line("Receitas", new Color(0, 126, 244), receitasDataPoints)); 
            dataLines.add(new Line("Despesas", new Color(255, 103, 103), despesasDataPoints)); 

        } catch (RuntimeException e) {
            System.err.println("Erro ao carregar dados para o gráfico de linha: " + e.getMessage());
            e.printStackTrace();
        }
        repaint(); 
    }

    private void checkHover(Point mousePoint) {
        LineData newHoveredPoint = null;
        String newValueText = null;
        int newHoveredX = -1;
        int newHoveredY = -1;

        if (dataLines == null || dataLines.isEmpty() || dataLines.get(0).getDataPoints().size() < 2) {
            if (hoveredPoint != null) {
                hoveredPoint = null;
                hoveredValueText = null;
                hoveredX = -1;
                hoveredY = -1;
                repaint();
            }
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int plotWidth = width - (2 * PADDING) - LABEL_PADDING;
        int plotHeight = height - (2 * PADDING) - LABEL_PADDING;
        
        BigDecimal maxValue = BigDecimal.ZERO;
        for (Line line : dataLines) {
            for (LineData data : line.getDataPoints()) {
                if (data.getValue().compareTo(maxValue) > 0) {
                    maxValue = data.getValue();
                }
            }
        }
        maxValue = maxValue.multiply(new BigDecimal("1.20"));
        if (maxValue.compareTo(BigDecimal.ZERO) == 0) {
            maxValue = BigDecimal.ONE;
        }
        
        int numPointsX = dataLines.get(0).getDataPoints().size();
        double xScale = (double) plotWidth / (numPointsX - 1);
        double yScale = (double) plotHeight / maxValue.doubleValue();

        for (Line line : dataLines) {
            for (int i = 0; i < line.getDataPoints().size(); i++) {
                LineData currentPoint = line.getDataPoints().get(i);
                int xCoord = (int) (PADDING + LABEL_PADDING + i * xScale);
                int yCoord = (int) (height - PADDING - LABEL_PADDING - (currentPoint.getValue().doubleValue() * yScale));

                double distance = mousePoint.distance(xCoord, yCoord);

                if (distance <= HIT_RADIUS) {
                    newHoveredPoint = currentPoint;
                    newValueText = String.format("R$ %.2f", currentPoint.getValue()); 
                    newHoveredX = xCoord;
                    newHoveredY = yCoord;
                    break; 
                }
            }
            if (newHoveredPoint != null) {
                break; 
            }
        }

        // Se o estado do hover mudou, atualiza as variáveis de instância e solicita redesenho
        if (newHoveredPoint != hoveredPoint) {
            this.hoveredPoint = newHoveredPoint;
            this.hoveredValueText = newValueText;
            this.hoveredX = newHoveredX;
            this.hoveredY = newHoveredY;
            repaint(); 
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Suavização de bordas

        FontMetrics fm = g2.getFontMetrics();

        // Verificação de Dados e Mensagem de Erro/Vazio ---
        if (dataLines == null || dataLines.isEmpty() || dataLines.get(0).getDataPoints().size() < 2) {
            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Arial", Font.PLAIN, 16));
            String msg = "Dados do gráfico não disponíveis ou insuficientes.";
            int xMsg = (getWidth() - fm.stringWidth(msg)) / 2;
            int yMsg = getHeight() / 2;
            g2.drawString(msg, xMsg, yMsg);
            return; 
        }

        int width = getWidth();
        int height = getHeight();

        int plotWidth = width - (2 * PADDING) - LABEL_PADDING;
        int plotHeight = height - (2 * PADDING) - LABEL_PADDING;

        BigDecimal maxValue = BigDecimal.ZERO;
        for (Line line : dataLines) {
            for (LineData data : line.getDataPoints()) {
                if (data.getValue().compareTo(maxValue) > 0) {
                    maxValue = data.getValue();
                }
            }
        }
        maxValue = maxValue.multiply(new BigDecimal("1.20")); 
        if (maxValue.compareTo(BigDecimal.ZERO) == 0) {
            maxValue = BigDecimal.ONE; 
        }

        int numPointsX = dataLines.get(0).getDataPoints().size(); 
        double xScale = (double) plotWidth / (numPointsX - 1); 
        double yScale = (double) plotHeight / maxValue.doubleValue(); 

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1.5f)); 

        g2.drawLine(PADDING + LABEL_PADDING, PADDING, PADDING + LABEL_PADDING, height - PADDING - LABEL_PADDING);
        g2.drawLine(PADDING + LABEL_PADDING, height - PADDING - LABEL_PADDING, width - PADDING, height - PADDING - LABEL_PADDING);

        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i < numPointsX; i++) {
            String label = dataLines.get(0).getDataPoints().get(i).getLabel(); // Pega o rótulo do mês da primeira linha
            int x = (int) (PADDING + LABEL_PADDING + i * xScale);
            int labelWidth = fm.stringWidth(label);
            g2.drawString(label, x - labelWidth / 2, height - PADDING - LABEL_PADDING + TEXT_OFFSET + fm.getHeight());
        }

        g2.drawString("Mês", width / 2 - (fm.stringWidth("Mês") / 2), height - PADDING + 10);

        int numYLabels = 5; 
        for (int i = 0; i <= numYLabels; i++) {
            BigDecimal yValue = maxValue.multiply(new BigDecimal(String.valueOf(i)))
                                      .divide(new BigDecimal(String.valueOf(numYLabels)), BigDecimal.ROUND_HALF_UP);
            int y = (int) (height - PADDING - LABEL_PADDING - (yValue.doubleValue() * yScale));
            
            // Desenha a linha de grade horizontal 
            g2.setColor(new Color(220, 220, 220));
            g2.drawLine(PADDING + LABEL_PADDING, y, width - PADDING, y);

            // Desenha o rótulo do valor
            g2.setColor(Color.BLACK);
            String valueLabel = String.format("R$ %.0f", yValue); // Formata o valor sem casas decimais com "R$"
            g2.drawString(valueLabel, PADDING + LABEL_PADDING - fm.stringWidth(valueLabel) - TEXT_OFFSET, y + fm.getAscent() / 2 - 2);
        }

        g2.drawString("Valor (R$)", PADDING + LABEL_PADDING - 20, PADDING - 10);

        g2.setStroke(new BasicStroke(2.0f)); // Espessura da linha dos dados

        for (Line line : dataLines) {
            g2.setColor(line.getColor()); 
            for (int i = 0; i < line.getDataPoints().size(); i++) {
                LineData currentPoint = line.getDataPoints().get(i);
                int x1 = (int) (PADDING + LABEL_PADDING + i * xScale);
                int y1 = (int) (height - PADDING - LABEL_PADDING - (currentPoint.getValue().doubleValue() * yScale));

                g2.fillOval(x1 - POINT_RADIUS, y1 - POINT_RADIUS, 2 * POINT_RADIUS, 2 * POINT_RADIUS);

                if (i < line.getDataPoints().size() - 1) {
                    LineData nextPoint = line.getDataPoints().get(i + 1);
                    int x2 = (int) (PADDING + LABEL_PADDING + (i + 1) * xScale);
                    int y2 = (int) (height - PADDING - LABEL_PADDING - (nextPoint.getValue().doubleValue() * yScale));
                    g2.drawLine(x1, y1, x2, y2);
                }
            }
        }

        int legendX = width - PADDING - 120; // Posição X para o início da legenda
        int legendY = PADDING + 10;          // Posição Y para o início da legenda
        int legendHeight = 20;               // Altura de cada item da legenda (quadrado + texto)
        int legendSpacing = 5;               // Espaçamento entre os itens da legenda
        g2.setFont(new Font("Arial", Font.PLAIN, 12)); 
        
        for (int i = 0; i < dataLines.size(); i++) {
            Line line = dataLines.get(i);
            g2.setColor(line.getColor()); // Cor do quadrado da legenda
            // Desenha o quadrado colorido
            g2.fillRect(legendX, legendY + i * (legendHeight + legendSpacing), 15, 15);
            g2.setColor(Color.BLACK); // Cor do texto da legenda
            g2.drawString(line.getName(), legendX + 20, legendY + i * (legendHeight + legendSpacing) + 12);
        }

        // Desenhar o valor do ponto hovered 
        if (hoveredPoint != null && hoveredValueText != null && hoveredX != -1 && hoveredY != -1) {
            g2.setFont(new Font("Arial", Font.BOLD, 14)); 
            FontMetrics currentFontMetrics = g2.getFontMetrics(); 
            
            int textWidth = currentFontMetrics.stringWidth(hoveredValueText);
            int textHeight = currentFontMetrics.getHeight();

            int tooltipX = hoveredX + POINT_RADIUS + 5; 
            int tooltipY = hoveredY - textHeight / 2; 

            if (tooltipX + textWidth + 5 > getWidth() - PADDING) {
                tooltipX = hoveredX - POINT_RADIUS - textWidth - 5;
            }

            if (tooltipY < PADDING) {
                tooltipY = PADDING;
            }
            if (tooltipY + textHeight + 5 > getHeight() - PADDING) {
                tooltipY = getHeight() - PADDING - textHeight - 5;
            }

            g2.setColor(new Color(40, 41, 102, 200)); 
            g2.fillRoundRect(tooltipX - 3, tooltipY - currentFontMetrics.getAscent() + (currentFontMetrics.getAscent() - textHeight) / 2, textWidth + 6, textHeight + 6, 5, 5); 
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(tooltipX - 3, tooltipY - currentFontMetrics.getAscent() + (currentFontMetrics.getAscent() - textHeight) / 2, textWidth + 6, textHeight + 6, 5, 5); 

            g2.setColor(Color.WHITE);
            g2.drawString(hoveredValueText, tooltipX, tooltipY + currentFontMetrics.getAscent() / 2 + 2); 
        }
    }
}