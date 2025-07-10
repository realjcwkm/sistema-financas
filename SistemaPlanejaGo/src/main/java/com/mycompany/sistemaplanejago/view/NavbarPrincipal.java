package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.Frame;
import java.awt.Color; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import com.mycompany.sistemaplanejago.view.TelaLogin;
import com.formdev.flatlaf.FlatLightLaf;

public class NavbarPrincipal extends javax.swing.JFrame {
    
    private UsuarioController usuarioController;
    
    public NavbarPrincipal() {
        
        initComponents();
        usuarioController = new UsuarioController();
        
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        buttonCadastrar2.putClientProperty("JButton.arc", 999); 
        
                
        buttonCadastrar2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                buttonCadastrar2.setBackground(new Color(255, 160, 0)); // Laranja
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                buttonCadastrar2.setBackground(Color.WHITE); // Branca
            }
        });
    
    
    }

    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notificationBellPanel1 = new com.mycompany.sistemaplanejago.view.NotificationBellPanel();
        panelPrincipal = new javax.swing.JPanel();
        panelNavBar = new javax.swing.JPanel();
        buttonCadastrar2 = new javax.swing.JButton();
        buttonCadastrar3 = new javax.swing.JButton();
        buttonCadastrar4 = new javax.swing.JButton();
        labelPlanejaGo = new javax.swing.JLabel();
        avatarPanel1 = new com.mycompany.sistemaplanejago.view.AvatarPanel();
        panelCentralizador = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelNavBar.setBackground(new java.awt.Color(44, 41, 102));
        panelNavBar.setPreferredSize(new java.awt.Dimension(800, 70));
        panelNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCadastrar2.setBackground(new java.awt.Color(44, 41, 102));
        buttonCadastrar2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        buttonCadastrar2.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrar2.setText("Calculadora");
        buttonCadastrar2.setBorder(null);
        buttonCadastrar2.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonCadastrar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCadastrar2MouseClicked(evt);
            }
        });
        buttonCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrar2ActionPerformed(evt);
            }
        });
        panelNavBar.add(buttonCadastrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 130, 30));

        buttonCadastrar3.setBackground(new java.awt.Color(44, 41, 102));
        buttonCadastrar3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        buttonCadastrar3.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrar3.setText("Lançamentos");
        buttonCadastrar3.setBorder(null);
        buttonCadastrar3.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonCadastrar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCadastrar3MouseClicked(evt);
            }
        });
        buttonCadastrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrar3ActionPerformed(evt);
            }
        });
        panelNavBar.add(buttonCadastrar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 50));

        buttonCadastrar4.setBackground(new java.awt.Color(44, 41, 102));
        buttonCadastrar4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        buttonCadastrar4.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrar4.setText("Relatórios");
        buttonCadastrar4.setBorder(null);
        buttonCadastrar4.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonCadastrar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCadastrar4MouseClicked(evt);
            }
        });
        buttonCadastrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrar4ActionPerformed(evt);
            }
        });
        panelNavBar.add(buttonCadastrar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 130, 50));

        labelPlanejaGo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelPlanejaGo.setForeground(new java.awt.Color(255, 160, 81));
        labelPlanejaGo.setText("PlanejaGo");
        labelPlanejaGo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        panelNavBar.add(labelPlanejaGo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 22, -1, 30));

        javax.swing.GroupLayout avatarPanel1Layout = new javax.swing.GroupLayout(avatarPanel1);
        avatarPanel1.setLayout(avatarPanel1Layout);
        avatarPanel1Layout.setHorizontalGroup(
            avatarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        avatarPanel1Layout.setVerticalGroup(
            avatarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelNavBar.add(avatarPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());
        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar2MouseClicked

    private void buttonCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar2ActionPerformed

    private void buttonCadastrar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar3MouseClicked

    private void buttonCadastrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar3ActionPerformed

    private void buttonCadastrar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar4MouseClicked

    private void buttonCadastrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NavbarPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NavbarPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NavbarPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NavbarPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        try {
            // Defina o Look and Feel para FlatLaf
            // Você pode escolher FlatLightLaf(), FlatDarkLaf(), FlatDarculaLaf(), FlatIntelliJLaf()
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
            // OU, se você quiser uma aparência mais específica:
            // com.formdev.flatlaf.FlatLaf.registerCustomDefaultsSource( "seu.pacote.tema" ); // Se você tiver um tema customizado
            // javax.swing.UIManager.setLookAndFeel( new com.formdev.flatlaf.FlatLightLaf() );
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NavbarPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NavbarPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.sistemaplanejago.view.AvatarPanel avatarPanel1;
    private javax.swing.JButton buttonCadastrar2;
    private javax.swing.JButton buttonCadastrar3;
    private javax.swing.JButton buttonCadastrar4;
    private javax.swing.JLabel labelPlanejaGo;
    private com.mycompany.sistemaplanejago.view.NotificationBellPanel notificationBellPanel1;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
