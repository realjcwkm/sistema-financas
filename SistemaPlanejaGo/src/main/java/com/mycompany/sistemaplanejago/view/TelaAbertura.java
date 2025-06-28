package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.Frame;
import java.awt.Color; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import com.mycompany.sistemaplanejago.view.TelaLogin;

public class TelaAbertura extends javax.swing.JFrame {
    
    private UsuarioController usuarioController;
    
    public TelaAbertura() {
        
        initComponents();
        usuarioController = new UsuarioController();
        
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        buttonEntrar.putClientProperty("JButton.arc", 999); 
        
        
        buttonEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                buttonEntrar.setBackground(new Color(255, 160, 0)); // Laranja
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                buttonEntrar.setBackground(Color.WHITE); // Branca
            }
        });
    
    
    }

    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelNavBar = new javax.swing.JPanel();
        labelPlanejaGo = new javax.swing.JLabel();
        panelBtnEntrar = new javax.swing.JPanel();
        buttonCadastrar2 = new javax.swing.JButton();
        buttonCadastrar1 = new javax.swing.JButton();
        panelCentralizador = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelNavBar.setBackground(new java.awt.Color(44, 41, 102));
        panelNavBar.setPreferredSize(new java.awt.Dimension(800, 70));
        panelNavBar.setLayout(new java.awt.BorderLayout());

        labelPlanejaGo.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        labelPlanejaGo.setForeground(new java.awt.Color(255, 160, 81));
        labelPlanejaGo.setText("PlanejaGo");
        labelPlanejaGo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        panelNavBar.add(labelPlanejaGo, java.awt.BorderLayout.WEST);

        panelBtnEntrar.setBackground(new java.awt.Color(44, 41, 102));
        panelBtnEntrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 30));

        buttonCadastrar2.setBackground(new java.awt.Color(44, 41, 102));
        buttonCadastrar2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        buttonCadastrar2.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrar2.setText("Entrar");
        buttonCadastrar2.setBorder(null);
        buttonCadastrar2.setMaximumSize(new java.awt.Dimension(66, 29));
        buttonCadastrar2.setMinimumSize(new java.awt.Dimension(66, 29));
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
        panelBtnEntrar.add(buttonCadastrar2);

        buttonCadastrar1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        buttonCadastrar1.setForeground(new java.awt.Color(44, 41, 102));
        buttonCadastrar1.setText("Cadastrar");
        buttonCadastrar1.setBorder(null);
        buttonCadastrar1.setMaximumSize(new java.awt.Dimension(66, 29));
        buttonCadastrar1.setMinimumSize(new java.awt.Dimension(66, 29));
        buttonCadastrar1.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonCadastrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCadastrar1MouseClicked(evt);
            }
        });
        buttonCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrar1ActionPerformed(evt);
            }
        });
        panelBtnEntrar.add(buttonCadastrar1);

        panelNavBar.add(panelBtnEntrar, java.awt.BorderLayout.LINE_END);

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 40)); // NOI18N
        jLabel1.setText("Organize suas Finanças");

        jLabel2.setText("Cresça financeiramente");

        jLabel3.setText("O PlanejaGo é um sistema de gestão de finanças pessoais ");
        jLabel3.setMaximumSize(new java.awt.Dimension(683, 105));
        jLabel3.setMinimumSize(new java.awt.Dimension(683, 105));
        jLabel3.setPreferredSize(new java.awt.Dimension(683, 105));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        panelCentralizador.add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCentralizador.add(jPanel2, new java.awt.GridBagConstraints());

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

    private void buttonCadastrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar1MouseClicked
        // TODO add your handling code here:
        TelaCadastro telaCadastro = new TelaCadastro(); // Cria a nova tela
        telaCadastro.setVisible(true); // Torna a nova tela visível

        // Fecha a TelaLogin atual
        this.dispose(); // Fecha a tela de login
    }//GEN-LAST:event_buttonCadastrar1MouseClicked

    private void buttonCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar1ActionPerformed

    private void buttonCadastrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar2MouseClicked

    private void buttonCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar2ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAbertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAbertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAbertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAbertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAbertura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar1;
    private javax.swing.JButton buttonCadastrar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
