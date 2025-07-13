package com.mycompany.sistemaplanejago.componentes.graficoBarraHome;

import javax.swing.*;
import java.awt.*;

public class BarChartPanel extends JPanel{
    private BarData barData;
    private int barWidth = 60; 
    private int barSpacing = 30; 
    private int chartPadding = 40; 
    private double maxValue; 

    public BarChartPanel(BarData barData) {
        this.barData = barData;
        calculateMaxValue();
        setPreferredSize(new Dimension(
            (barData.getBars().size() * (barWidth + barSpacing)) + (2 * chartPadding),
            300 
        ));

        setBackground(new Color(229, 229, 246)); 
    }

    private void calculateMaxValue() {
        maxValue = 0;
        for (Bar bar : barData.getBars()) {
            if (bar.getValue() > maxValue) {
                maxValue = bar.getValue();
            }
        }

        maxValue *= 1.1;
        if (maxValue == 0) { 
            maxValue = 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelHeight = getHeight();
        int panelWidth = getWidth();

        g2d.setColor(Color.BLACK);
        g2d.drawLine(chartPadding, panelHeight - chartPadding, panelWidth - chartPadding, panelHeight - chartPadding);

        g2d.drawLine(chartPadding, chartPadding, chartPadding, panelHeight - chartPadding);

        int x = chartPadding + barSpacing;

        // Desenha as barras
        for (Bar bar : barData.getBars()) {
            int barHeight = (int) ((bar.getValue() / maxValue) * (panelHeight - (2 * chartPadding)));
            int y = panelHeight - chartPadding - barHeight;

            g2d.setColor(bar.getColor()); 
            g2d.fillRect(x, y, barWidth, barHeight);

            // valor da barra
            g2d.setColor(Color.BLACK);
            String valueText = String.format("%.0f", bar.getValue());
            FontMetrics fm = g2d.getFontMetrics();
            int valueWidth = fm.stringWidth(valueText);
            g2d.drawString(valueText, x + (barWidth - valueWidth) / 2, y - 5);

            // rótulo da barra
            String labelText = bar.getLabel();
            int labelWidth = fm.stringWidth(labelText);
            g2d.drawString(labelText, x + (barWidth - labelWidth) / 2, panelHeight - chartPadding + 15);

            x += barWidth + barSpacing;
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("Valores", chartPadding + 5, chartPadding - 10);
    }
}
