/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaplanejago.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class AvatarPanel extends JPanel {
    private String text;
    private Color bgColor;
    private Color textColor;
    private Font textFont;
    
    public AvatarPanel() {
        // Você pode chamar o outro construtor com valores padrão, se quiser:
        this("U", new Color(229, 229, 246), new Color(44, 41, 102), new Font("Roboto", Font.BOLD, 20)); // Exemplo de valores padrão
    }

    public AvatarPanel(String text, Color bgColor, Color textColor, Font textFont) {
        this.text = text;
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.textFont = textFont;
        setPreferredSize(new Dimension(40, 40));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2d.setColor(bgColor);
        g2d.fillOval(x, y, diameter, diameter);

        g2d.setColor(textColor);
        g2d.setFont(textFont);
        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (diameter - fm.stringWidth(text)) / 2;
        int textY = y + (diameter - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(text, textX, textY);

        g2d.dispose();
    }
}
