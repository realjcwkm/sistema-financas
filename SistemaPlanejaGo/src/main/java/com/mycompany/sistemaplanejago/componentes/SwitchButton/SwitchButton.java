package com.mycompany.sistemaplanejago.componentes.SwitchButton;

import java.awt.AlphaComposite; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;


public class SwitchButton extends javax.swing.JPanel {

    private boolean selected = false; 
    private Color switchOnColor = new Color(0, 153, 51); // Verde 
    private Color switchOffColor = new Color(220, 0, 0); // Vermelho 
    
    private Color disableColor = new Color(190, 190, 190);
    private int thumbMargin = 3; 
    private int roundArc = 999; 

    private Font textFont = new Font("Segoe UI", Font.BOLD, 16); 

    private final List<SwitchListener> events = new ArrayList<>();

    public SwitchButton() {
        setOpaque(false);
        setPreferredSize(new Dimension(120, 40)); 
        setMinimumSize(new Dimension(120, 40));
        setMaximumSize(new Dimension(120, 40));
        setFont(textFont); 

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isEnabled() && SwingUtilities.isLeftMouseButton(e)) {
                    setSelected(!selected); // Alterna o estado
                    fireSelectChange();
                }
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void addSwitchListener(SwitchListener listener) {
        events.add(listener);
    }

    private void fireSelectChange() {
        for (SwitchListener event : events) {
            event.selectChange(selected);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Desenha o fundo padrão do JPanel

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(textFont); // Define a fonte para o desenho do texto

        int width = getWidth();
        int height = getHeight();
        int arcSize = roundArc == 999 ? height : roundArc;

        Color bgColor = isSelected() ? switchOnColor : switchOffColor;
        g2.setColor(isEnabled() ? bgColor : disableColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, arcSize, arcSize));

        // 2. Desenha a bolinha branca
        int thumbDiameter = height - (thumbMargin * 2); // Diâmetro do thumb
        int thumbX;
        
        if (isSelected()) { // Estado ON (Direita)
            thumbX = width - thumbDiameter - thumbMargin;
        } else { // Estado OFF (Esquerda)
            thumbX = thumbMargin;
        }

        g2.setColor(Color.WHITE); // A cor da bolinha
        g2.fillOval(thumbX, thumbMargin, thumbDiameter, thumbDiameter);

        // 3. Desenha os textos nas extremidades
        FontMetrics fm = g2.getFontMetrics();
        int textY = (height - fm.getAscent()) / 2 + fm.getAscent(); 
        String textLabelPaga = "Não Paga"; 
        String textLabelNaoPaga = "Paga";

        int pagaTextWidth = fm.stringWidth(textLabelPaga);
        int naoPagaTextWidth = fm.stringWidth(textLabelNaoPaga);

        // --- CÁLCULO DE POSIÇÃO PARA DEIXAR O TEXTO MAIS PRÓXIMO DA BEIRADA ---
        // Margem em pixels do texto até a borda lateral do botão
        int textHorizontalPadding = 5; // Ajuste este valor conforme necessário

        // Posição para o texto "PAGA" 
        int pagaTextX = width - pagaTextWidth - textHorizontalPadding;
        
        // Posição para o texto "NÃO PAGA"
        int naoPagaTextX = textHorizontalPadding;
        
        // --- Lógica de Visibilidade e Cores do Texto ---

        // Desenha "PAGA" (Este texto está no lado direito)
        if (isSelected()) { // Se o botão está ON (bolinha à direita)
            // Texto "Paga" está INATIVO (longe da bolinha). Deve ser INVISÍVEL
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f)); 
        } else { // Se o botão está OFF (bolinha à esquerda)
            // Texto "Paga" está ATIVO (longe da bolinha). Cor BRANCA
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        g2.setColor(Color.WHITE); 
        g2.drawString(textLabelPaga, pagaTextX, textY);


        // Desenha "Não Paga" (Este texto está no lado esquerdo)
        if (isSelected()) { // Se o botão está ON (bolinha à direita)
            // Texto "Não Paga" está ATIVO (longe da bolinha). Cor BRANCA
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else { // Se o botão está OFF (bolinha à esquerda)
            // Texto "Não Paga" está INATIVO (longe da bolinha). Deve ser INVISÍVEL
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        g2.setColor(Color.WHITE); // Texto NÃO PAGA sempre branco
        g2.drawString(textLabelNaoPaga, naoPagaTextX, textY);

        g2.dispose();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
    }

    @Override
    public void setFont(Font font) {
        this.textFont = font;
        super.setFont(font);
    }
}