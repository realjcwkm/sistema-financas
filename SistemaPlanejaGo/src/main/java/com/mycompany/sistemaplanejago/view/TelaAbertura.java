package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.Frame;
import java.awt.Color; // Certifique-se que esta linha está no topo do seu arquivo com os outros imports
import java.awt.event.MouseAdapter; // E esta linha
import java.awt.event.MouseEvent; // E esta linha
import com.mycompany.sistemaplanejago.view.TelaCadastro;

public class TelaAbertura extends javax.swing.JFrame {
    
    private UsuarioController usuarioController;
    
    public TelaAbertura() {
        initComponents();
        usuarioController = new UsuarioController();
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        buttonCadastrar1.putClientProperty("JButton.arc", 999); //não tá arredondando muito
        
        
        buttonCadastrar1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                // Quando o mouse ENTRA no botão, muda a cor para laranja
                buttonCadastrar1.setBackground(new Color(255, 160, 0)); // Laranja
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                // Quando o mouse SAI do botão, volta para a cor original (branca)
                buttonCadastrar1.setBackground(Color.WHITE); // Cor original do botão
            }
        });
        
        buttonCadastrar2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                // Quando o mouse ENTRA no botão, muda a cor para laranja
                buttonCadastrar2.setForeground(new Color(255, 160, 0)); // Laranja
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                // Quando o mouse SAI do botão, volta para a cor original (branca)
                buttonCadastrar2.setForeground(Color.WHITE); // Cor original do botão
            }
        });
    
    
    }

    
    
    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelCentralizador = new javax.swing.JPanel();
        panelContentLeft = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelNavBar = new javax.swing.JPanel();
        labelPlanejaGo = new javax.swing.JLabel();
        panelBtnEntrar = new javax.swing.JPanel();
        buttonCadastrar2 = new javax.swing.JButton();
        buttonCadastrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));

        panelContentLeft.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 160, 81));
        jLabel3.setText("Cresça financeiramente");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(19, 16, 71));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("O PlanejaGo é um sistema de gestão de finanças pessoais que te ajuda a gerir o controle de despesas de forma simples e fácil.");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(97, 90, 205));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Organize suas Finanças");

        jButton1.setBackground(new java.awt.Color(97, 90, 205));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Começar");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelContentLeftLayout = new javax.swing.GroupLayout(panelContentLeft);
        panelContentLeft.setLayout(panelContentLeftLayout);
        panelContentLeftLayout.setHorizontalGroup(
            panelContentLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLeftLayout.createSequentialGroup()
                .addGroup(panelContentLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLeftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelContentLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelContentLeftLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelContentLeftLayout.setVerticalGroup(
            panelContentLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLeftLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(97, 90, 205));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelCentralizadorLayout = new javax.swing.GroupLayout(panelCentralizador);
        panelCentralizador.setLayout(panelCentralizadorLayout);
        panelCentralizadorLayout.setHorizontalGroup(
            panelCentralizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralizadorLayout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(panelContentLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCentralizadorLayout.setVerticalGroup(
            panelCentralizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCentralizadorLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(panelContentLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

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

        buttonCadastrar1.setBackground(new java.awt.Color(255, 255, 255));
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

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrar2MouseClicked
        // TODO add your handling code here:
        new TelaLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCadastrar2MouseClicked

    private void buttonCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrar2ActionPerformed

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

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setBackground(new Color(255, 160, 0));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setBackground(new Color(97, 90, 205));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        new TelaCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelContentLeft;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
