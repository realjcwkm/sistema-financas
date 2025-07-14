/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.sistemaplanejago.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape; // Importar Shape
import java.awt.geom.Ellipse2D; // Importar Ellipse2D para o círculo
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class NotificationBellPanel extends JPanel {

    private ImageIcon bellIcon;
    private int notificationCount = 0;

    public NotificationBellPanel() {
        setOpaque(false); // Garante a transparência do painel
        setPreferredSize(new Dimension(40, 40)); // Tamanho padrão
    }

    public void setBellIcon(ImageIcon bellIcon) {
        this.bellIcon = bellIcon;
        repaint(); // Redesenha o painel quando o ícone é definido
    }

    public void setNotificationCount(int count) {
        this.notificationCount = count;
        repaint(); // Redesenha para atualizar a contagem
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Garante que o fundo transparente seja desenhado
        Graphics2D g2d = (Graphics2D) g.create(); // Cria uma cópia para não afetar o Graphics original

        // Habilita anti-aliasing para suavizar as bordas (importante para círculos e imagens)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (bellIcon != null && bellIcon.getImage() != null) {
            // Calculate the size for the circular icon, consider padding if needed
            int diameter = Math.min(getWidth(), getHeight()) - 4; // Subtrai uma pequena margem (2px de cada lado)
            if (diameter <= 0) diameter = 1; // Evitar diâmetro zero ou negativo

            // Calculate position to center the circle within the panel
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            // Save the original clip (important!)
            Shape originalClip = g2d.getClip();

            // Create and set a circular clip for drawing the image
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
            g2d.setClip(circle);

            // Get the original image and scale it to fit the circular area
            Image img = bellIcon.getImage();
            Image scaledImg = img.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH); // Escala para o diâmetro do círculo

            // !!! CUIDADO: NãO crie um novo ImageIcon aqui. Desenhe a Image diretamente. !!!
            // !!! REMOVIDO: ImageIcon scaledBellIcon = new ImageIcon(scaledImg); !!!
            // !!! REMOVIDO: scaledBellIcon.paint(this, g2d, iconX, iconY); !!!

            // Desenha a imagem escalada. Ela será automaticamente cortada pela forma circular definida pelo setClip().
            g2d.drawImage(scaledImg, x, y, diameter, diameter, this);

            // Restore the original clip (IMPORTANT!)
            g2d.setClip(originalClip);
        }

        // --- Código para a bolha de notificação (já existente) ---
        if (notificationCount > 0) {
            String countText = String.valueOf(notificationCount);
            g2d.setColor(Color.RED); // Cor vermelha para a bolha
            g2d.setFont(new Font("SansSerif", Font.BOLD, 10));

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(countText);
            int textHeight = fm.getHeight();

            int bubbleSize = Math.max(textWidth, textHeight) + 6; // +6 para padding
            int bubbleX = getWidth() - bubbleSize - 3; // Posição X da bolha
            int bubbleY = 3; // Posição Y da bolha

            g2d.fillOval(bubbleX, bubbleY, bubbleSize, bubbleSize); // Desenha a bolha

            g2d.setColor(Color.WHITE); // Cor do texto da notificação
            // Centraliza o texto na bolha
            int textX = bubbleX + (bubbleSize - textWidth) / 2;
            int textY = bubbleY + (bubbleSize - textHeight) / 2 + fm.getAscent();
            g2d.drawString(countText, textX, textY);
        }

        g2d.dispose(); // Libera os recursos gráficos
    }
}