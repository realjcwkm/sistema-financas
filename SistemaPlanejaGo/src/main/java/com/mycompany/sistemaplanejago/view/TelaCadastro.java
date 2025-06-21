package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.Frame;
import java.awt.Color; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import com.mycompany.sistemaplanejago.view.TelaLogin;

public class TelaCadastro extends javax.swing.JFrame {
    
    private UsuarioController usuarioController;
    
    public TelaCadastro() {
        
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
        buttonEntrar = new javax.swing.JButton();
        panelCentralizador = new javax.swing.JPanel();
        panelContent = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelNome = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        fieldNome = new javax.swing.JTextField();
        panelEmail = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        fieldEmail = new javax.swing.JTextField();
        panelDtNascimento = new javax.swing.JPanel();
        labelDtNascimento = new javax.swing.JLabel();
        fieldDtNascimento = new javax.swing.JTextField();
        panelSenha = new javax.swing.JPanel();
        labelSenha = new javax.swing.JLabel();
        fieldSenha = new javax.swing.JPasswordField();
        panelCadastrar = new javax.swing.JPanel();
        buttonCadastrar = new javax.swing.JButton();

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

        buttonEntrar.setBackground(new java.awt.Color(255, 255, 255));
        buttonEntrar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        buttonEntrar.setForeground(new java.awt.Color(44, 41, 102));
        buttonEntrar.setText("Entrar");
        buttonEntrar.setBorder(null);
        buttonEntrar.setPreferredSize(new java.awt.Dimension(180, 40));
        buttonEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEntrarMouseClicked(evt);
            }
        });
        buttonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntrarActionPerformed(evt);
            }
        });
        panelBtnEntrar.add(buttonEntrar);

        panelNavBar.add(panelBtnEntrar, java.awt.BorderLayout.LINE_END);

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        panelContent.setLayout(new java.awt.GridLayout(0, 1));

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(90, 90, 205));
        labelTitulo.setText("Crie sua Conta");
        panelTitulo.add(labelTitulo);

        panelContent.add(panelTitulo);

        panelNome.setBackground(new java.awt.Color(255, 255, 255));

        labelNome.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelNome.setForeground(new java.awt.Color(19, 16, 71));
        labelNome.setText("Nome");

        fieldNome.setBackground(new java.awt.Color(255, 255, 255));
        fieldNome.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNomeLayout = new javax.swing.GroupLayout(panelNome);
        panelNome.setLayout(panelNomeLayout);
        panelNomeLayout.setHorizontalGroup(
            panelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNome)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelNomeLayout.setVerticalGroup(
            panelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNomeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelContent.add(panelNome);

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
                .addComponent(fieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelContent.add(panelEmail);

        panelDtNascimento.setBackground(new java.awt.Color(255, 255, 255));

        labelDtNascimento.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelDtNascimento.setForeground(new java.awt.Color(19, 16, 71));
        labelDtNascimento.setText("Data de Nascimento");

        fieldDtNascimento.setBackground(new java.awt.Color(255, 255, 255));
        fieldDtNascimento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldDtNascimento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldDtNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDtNascimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDtNascimentoLayout = new javax.swing.GroupLayout(panelDtNascimento);
        panelDtNascimento.setLayout(panelDtNascimentoLayout);
        panelDtNascimentoLayout.setHorizontalGroup(
            panelDtNascimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDtNascimentoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelDtNascimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDtNascimento)
                    .addComponent(fieldDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelDtNascimentoLayout.setVerticalGroup(
            panelDtNascimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDtNascimentoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelDtNascimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDtNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelContent.add(panelDtNascimento);

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
                .addGap(18, 18, 18)
                .addGroup(panelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addContainerGap())
        );
        panelSenhaLayout.setVerticalGroup(
            panelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSenhaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelSenha)
                .addGap(6, 6, 6)
                .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContent.add(panelSenha);

        panelCadastrar.setBackground(new java.awt.Color(255, 255, 255));

        buttonCadastrar.setBackground(new java.awt.Color(97, 90, 205));
        buttonCadastrar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        buttonCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrar.setText("Entrar");
        buttonCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCadastrarMouseExited(evt);
            }
        });
        buttonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCadastrarLayout = new javax.swing.GroupLayout(panelCadastrar);
        panelCadastrar.setLayout(panelCadastrarLayout);
        panelCadastrarLayout.setHorizontalGroup(
            panelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastrarLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(buttonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCadastrarLayout.setVerticalGroup(
            panelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastrarLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(buttonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelContent.add(panelCadastrar);

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
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEntrarActionPerformed

    private void fieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNomeActionPerformed

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed
        // TODO add your handling code here:
        String nome = fieldNome.getText();
        String email = fieldEmail.getText();
        String dataNascimentoStr = fieldDtNascimento.getText();
        String senha = new String(fieldSenha.getPassword()); 

        // Se der certo, chama o método do controller
        boolean sucesso = usuarioController.cadastrarUsuario(nome, email, dataNascimentoStr, senha);

        // Limpa os campos -> não funciou corrigir em outra branch
        if (sucesso) {
            fieldNome.setText("");
            fieldEmail.setText("");
            fieldDtNascimento.setText("");
            fieldSenha.setText("");
        }
    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void buttonCadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrarMouseEntered
        // TODO add your handling code here:
        buttonCadastrar.setBackground(new Color(255, 165, 0));
        
    }//GEN-LAST:event_buttonCadastrarMouseEntered

    private void buttonCadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCadastrarMouseExited
        // TODO add your handling code here:
        buttonCadastrar.setBackground(new Color(97, 90, 205));
    }//GEN-LAST:event_buttonCadastrarMouseExited

    private void fieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmailActionPerformed

    private void fieldDtNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDtNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDtNascimentoActionPerformed

    private void fieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSenhaActionPerformed

    private void buttonEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEntrarMouseClicked
        // TODO add your handling code here:
        TelaLogin telaLogin = new TelaLogin (); 
        telaLogin.setVisible(true); 

        this.dispose(); 
    }//GEN-LAST:event_buttonEntrarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JButton buttonEntrar;
    private javax.swing.JTextField fieldDtNascimento;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JPasswordField fieldSenha;
    private javax.swing.JLabel labelDtNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelCadastrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelDtNascimento;
    private javax.swing.JPanel panelEmail;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelNome;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSenha;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
