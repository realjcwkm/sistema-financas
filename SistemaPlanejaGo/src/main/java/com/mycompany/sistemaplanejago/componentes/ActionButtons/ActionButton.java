package com.mycompany.sistemaplanejago.componentes.ActionButtons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
//mport javax.swing.ImageIcon; 
//import java.awt.Image;     
//import java.io.InputStream;   
//import javax.imageio.ImageIO; 

public class ActionButton extends JButton {

    private boolean mousePress;

    public ActionButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                mousePress = true;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                mousePress = false;
            }
        });
        
        setPreferredSize(new Dimension(38, 38)); 
        setMinimumSize(new Dimension(38, 38));
        setMaximumSize(new Dimension(38, 38));
    }

    public void setActionIcon(String iconPath) {
        try {
            // Pra tratar o erro na hora de carregar as imagens 
            // Depois verifico como arrumar 
            
            //InputStream is = getClass().getResourceAsStream(iconPath);
            //if (is != null) {
                //System.out.println("DEBUG: InputStream para '" + iconPath + "' encontrado."); 
                //Image originalImage = ImageIO.read(is);
                //if (originalImage != null) {
                    //System.out.println("DEBUG: Imagem '" + iconPath + "' decodificada com sucesso. Dimensões originais: " + originalImage.getWidth(null) + "x" + originalImage.getHeight(null));     
                    //Image scaledImage = originalImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH); 
                    //setIcon(new ImageIcon(scaledImage));
                //} else {
                    //System.err.println("ERRO CRÍTICO: ImageIO não conseguiu decodificar imagem de '" + iconPath + "'. Arquivo corrompido ou formato inválido.");
                    //setText("?");
                //}
            //} else {
                //System.err.println("ERRO CRÍTICO: Recurso '" + iconPath + "' é NULL (arquivo não encontrado no JAR ou classpath).");
                //setText("?");
            //}
        } catch (Exception ex) {
            System.err.println("ERRO CRÍTICO GERAL ao carregar ícone '" + iconPath + "': " + ex.getMessage());
            setText("!");
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height); 
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        
        if (mousePress) {
            g2.setColor(new Color(158, 158, 158));
        } else {
            g2.setColor(new Color(199, 199, 199));
        }
        g2.fill(new Ellipse2D.Double(x, y, size, size)); 
        g2.dispose();
        
        super.paintComponent(grphcs); 
    }
}