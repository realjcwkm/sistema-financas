package com.mycompany.sistemaplanejago;

import com.formdev.flatlaf.FlatLightLaf; 
import com.mycompany.sistemaplanejago.view.TelaLogin; 

public class SistemaPlanejaGo {

    public static void main(String args[]) {
        
        try {
           
            FlatLightLaf.setup();

        } catch (Exception ex) {
          
            java.util.logging.Logger.getLogger(SistemaPlanejaGo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin(); // Cria sua tela de login
                telaLogin.setVisible(true); // Faz ela aparecer
            }
        });
    }
}