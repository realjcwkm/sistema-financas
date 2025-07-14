package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.Frame;
import java.awt.Color; // Certifique-se que esta linha está no topo do seu arquivo com os outros imports
import java.awt.event.MouseAdapter; // E esta linha
import java.awt.event.MouseEvent; // E esta linha
import com.mycompany.sistemaplanejago.view.TelaCadastro;

public class TelaLogin extends javax.swing.JFrame {
    
    private UsuarioController usuarioController;
    
    public TelaLogin() {
        initComponents();
         usuarioController = new UsuarioController();
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        buttonCadastrar.putClientProperty("JButton.arc", 999); //não tá arredondando muito
        
        
        buttonCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                // Quando o mouse ENTRA no botão, muda a cor para laranja
                buttonCadastrar.setBackground(new Color(255, 160, 0)); // Laranja
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                // Quando o mouse SAI do botão, volta para a cor original (branca)
                buttonCadastrar.setBackground(Color.WHITE); // Cor original do botão
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
        panelBtnCadastrar = new javax.swing.JPanel();
        buttonCadastrar = new javax.swing.JButton();
        panelCentralizador = new javax.swing.JPanel();
        panelContent = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelEmail = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        fieldEmail = new javax.swing.JTextField();
        panelSenha = new javax.swing.JPanel();
        labelSenha = new javax.swing.JLabel();
        fieldSenha = new javax.swing.JPasswordField();
        panelEsqueciSenha = new javax.swing.JPanel();
        panelEntar = new javax.swing.JPanel();
        buttonEntrar1 = new javax.swing.JButton();

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
        labelPlanejaGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlanejaGoMouseClicked(evt);
            }
        });
        panelNavBar.add(labelPlanejaGo, java.awt.BorderLayout.WEST);

        panelBtnCadastrar.setBackground(new java.awt.Color(44, 41, 102));
        panelBtnCadastrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 30));

        buttonCadastrar.setBackground(new java.awt.Color(255, 255, 255));
        buttonCadastrar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        buttonCadastrar.setForeground(new java.awt.Color(44, 41, 102));
        buttonCadastrar.setText("Cadastrar");
        buttonCadastrar.setBorder(null);
        buttonCadastrar.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCadastrarMouseClicked(evt);
            }
        });
        buttonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarActionPerformed(evt);
            }
        });
        panelBtnCadastrar.add(buttonCadastrar);

        panelNavBar.add(panelBtnCadastrar, java.awt.BorderLayout.LINE_END);

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        panelContent.setLayout(new java.awt.GridLayout(0, 1));

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(90, 90, 205));
        labelTitulo.setText("Entre na sua Conta");
        panelTitulo.add(labelTitulo);

        panelContent.add(panelTitulo);

        panelEmail.setBackground(new java.awt.Color(255, 255, 255));

        labelEmail.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(19, 16, 71));
        labelEmail.setText("Email");

        fieldEmail.setBackground(new java.awt.Color(255, 255, 255));
        fieldEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmailLayout = new javax.swing.GroupLayout(panelEmail);
        panelEmail.setLayout(panelEmailLayout);
        panelEmailLayout.setHorizontalGroup(
            panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmailLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmail)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelEmailLayout.setVerticalGroup(
            panelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmailLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelContent.add(panelEmail);

        panelSenha.setBackground(new java.awt.Color(255, 255, 255));
        panelSenha.setPreferredSize(new java.awt.Dimension(418, 95));

        labelSenha.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelSenha.setForeground(new java.awt.Color(19, 16, 71));
        labelSenha.setText("Senha");

        fieldSenha.setBackground(new java.awt.Color(255, 255, 255));
        fieldSenha.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldSenha.setName("txtSenha"); // NOI18N
        fieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSenhaLayout = new javax.swing.GroupLayout(panelSenha);
        panelSenha.setLayout(panelSenhaLayout);
        panelSenhaLayout.setHorizontalGroup(
            panelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSenhaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSenha)
                    .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelSenhaLayout.setVerticalGroup(
            panelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSenhaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelSenha)
                .addGap(6, 6, 6)
                .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContent.add(panelSenha);

        panelEsqueciSenha.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelEsqueciSenhaLayout = new javax.swing.GroupLayout(panelEsqueciSenha);
        panelEsqueciSenha.setLayout(panelEsqueciSenhaLayout);
        panelEsqueciSenhaLayout.setHorizontalGroup(
            panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        panelEsqueciSenhaLayout.setVerticalGroup(
            panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        panelContent.add(panelEsqueciSenha);

        panelEntar.setBackground(new java.awt.Color(255, 255, 255));

        buttonEntrar1.setBackground(new java.awt.Color(97, 90, 205));
        buttonEntrar1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        buttonEntrar1.setForeground(new java.awt.Color(255, 255, 255));
        buttonEntrar1.setText("Entrar");
        buttonEntrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonEntrar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonEntrar1MouseExited(evt);
            }
        });
        buttonEntrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEntarLayout = new javax.swing.GroupLayout(panelEntar);
        panelEntar.setLayout(panelEntarLayout);
        panelEntarLayout.setHorizontalGroup(
            panelEntarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntarLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(buttonEntrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelEntarLayout.setVerticalGroup(
            panelEntarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonEntrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelContent.add(panelEntar);

        panelCentralizador.add(panelContent, new java.awt.GridBagConstraints());

        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void fieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmailActionPerformed

    private void buttonEntrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntrar1ActionPerformed
        // TODO add your handling code here:
        String email = fieldEmail.getText(); 
        String senha = new String(fieldSenha.getPassword());
        
        boolean loginSucesso = usuarioController.logarUsuario(email, senha);
        
        if (loginSucesso == true){
            new TelaHome().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_buttonEntrar1ActionPerformed

    private void fieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSenhaActionPerformed

    private void buttonEntrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEntrar1MouseEntered
        // TODO add your handling code here:
        buttonEntrar1.setBackground(new Color(255, 165, 0));
    }//GEN-LAST:event_buttonEntrar1MouseEntered

    private void buttonEntrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEntrar1MouseExited
        // TODO add your handling code here:
        buttonEntrar1.setBackground(new Color(97, 90, 205));
    }//GEN-LAST:event_buttonEntrar1MouseExited

    private void buttonCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrarMouseClicked
        // TODO add your handling code here:
        TelaCadastro telaCadastro = new TelaCadastro(); // Cria a nova tela
        telaCadastro.setVisible(true); // Torna a nova tela visível

        // Fecha a TelaLogin atual
        this.dispose(); // Fecha a tela de login
    }//GEN-LAST:event_buttonCadastrarMouseClicked

    private void labelPlanejaGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlanejaGoMouseClicked
        // TODO add your handling code here:
        new TelaAbertura().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelPlanejaGoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JButton buttonEntrar1;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JPasswordField fieldSenha;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBtnCadastrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelEmail;
    private javax.swing.JPanel panelEntar;
    private javax.swing.JPanel panelEsqueciSenha;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSenha;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
