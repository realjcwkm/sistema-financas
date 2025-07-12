package com.mycompany.sistemaplanejago.view;

public class TelaHome extends javax.swing.JFrame {

    public TelaHome() {
        
        initComponents();  
      
    }
    
    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelNavBar = new javax.swing.JPanel();
        labelPlanejaGo = new javax.swing.JLabel();
        panelBtnCadastrar = new javax.swing.JPanel();
        panelCentralizador = new javax.swing.JPanel();
        panelContent = new javax.swing.JPanel();
        panelRow1 = new javax.swing.JPanel();
        panelRow1Left = new javax.swing.JPanel();
        labelCumprimento = new javax.swing.JLabel();
        labelFraseDia = new javax.swing.JLabel();
        panelRowRight = new javax.swing.JPanel();
        labelAcessoRapido = new javax.swing.JLabel();
        labelNovaDespesa = new javax.swing.JLabel();
        labelNovaReceita = new javax.swing.JLabel();
        panelRow2 = new javax.swing.JPanel();
        panelTotalPagar = new javax.swing.JPanel();
        labelTotalPagar = new javax.swing.JLabel();
        labelValorTotalPagar = new javax.swing.JLabel();
        panelTotalReceita = new javax.swing.JPanel();
        labelTotalReceita = new javax.swing.JLabel();
        labelValorReceita = new javax.swing.JLabel();
        panelTotalPago = new javax.swing.JPanel();
        labelTotalPago = new javax.swing.JLabel();
        labelValorTotalPago = new javax.swing.JLabel();
        panelRow3 = new javax.swing.JPanel();
        panelRegiaoLinha = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelConfiguracaoLinhas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelRegiaoBarra = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        panelBtnCadastrar.setBackground(new java.awt.Color(44, 41, 102));
        panelBtnCadastrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 30));
        panelNavBar.add(panelBtnCadastrar, java.awt.BorderLayout.LINE_END);

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        panelContent.setBackground(new java.awt.Color(255, 255, 255));

        panelRow1.setBackground(new java.awt.Color(255, 255, 255));

        panelRow1Left.setBackground(new java.awt.Color(255, 255, 255));

        labelCumprimento.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelCumprimento.setForeground(new java.awt.Color(97, 90, 205));
        labelCumprimento.setText("Olá User!");

        labelFraseDia.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        labelFraseDia.setForeground(new java.awt.Color(46, 43, 104));
        labelFraseDia.setText("Poupar hoje é garantir tranquilidade amanhã.");

        javax.swing.GroupLayout panelRow1LeftLayout = new javax.swing.GroupLayout(panelRow1Left);
        panelRow1Left.setLayout(panelRow1LeftLayout);
        panelRow1LeftLayout.setHorizontalGroup(
            panelRow1LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCumprimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelFraseDia, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        panelRow1LeftLayout.setVerticalGroup(
            panelRow1LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRow1LeftLayout.createSequentialGroup()
                .addComponent(labelCumprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFraseDia)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelRowRight.setBackground(new java.awt.Color(229, 229, 246));

        labelAcessoRapido.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        labelAcessoRapido.setForeground(new java.awt.Color(44, 41, 102));
        labelAcessoRapido.setText("Acesso Rápido");

        labelNovaDespesa.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelNovaDespesa.setForeground(new java.awt.Color(44, 41, 102));
        labelNovaDespesa.setText("+ Criar Nova Despesa");

        labelNovaReceita.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelNovaReceita.setForeground(new java.awt.Color(44, 41, 102));
        labelNovaReceita.setText("+ Criar Nova Receita");

        javax.swing.GroupLayout panelRowRightLayout = new javax.swing.GroupLayout(panelRowRight);
        panelRowRight.setLayout(panelRowRightLayout);
        panelRowRightLayout.setHorizontalGroup(
            panelRowRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRowRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNovaDespesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNovaReceita)
                .addGap(25, 25, 25))
            .addGroup(panelRowRightLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(labelAcessoRapido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRowRightLayout.setVerticalGroup(
            panelRowRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRowRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAcessoRapido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRowRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNovaDespesa)
                    .addComponent(labelNovaReceita))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRow1Layout = new javax.swing.GroupLayout(panelRow1);
        panelRow1.setLayout(panelRow1Layout);
        panelRow1Layout.setHorizontalGroup(
            panelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRow1Left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRowRight, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRow1Layout.setVerticalGroup(
            panelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRow1Layout.createSequentialGroup()
                .addGroup(panelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRow1Left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRowRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelRow2.setBackground(new java.awt.Color(255, 255, 255));

        panelTotalPagar.setBackground(new java.awt.Color(229, 229, 246));

        labelTotalPagar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelTotalPagar.setForeground(new java.awt.Color(46, 43, 104));
        labelTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalPagar.setText("Total a Pagar");
        labelTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelValorTotalPagar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelValorTotalPagar.setForeground(new java.awt.Color(255, 103, 103));
        labelValorTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorTotalPagar.setText("R$ 820,00");

        javax.swing.GroupLayout panelTotalPagarLayout = new javax.swing.GroupLayout(panelTotalPagar);
        panelTotalPagar.setLayout(panelTotalPagarLayout);
        panelTotalPagarLayout.setHorizontalGroup(
            panelTotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTotalPagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
            .addComponent(labelValorTotalPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTotalPagarLayout.setVerticalGroup(
            panelTotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalPagarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelValorTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelTotalReceita.setBackground(new java.awt.Color(229, 229, 246));

        labelTotalReceita.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelTotalReceita.setForeground(new java.awt.Color(46, 43, 104));
        labelTotalReceita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalReceita.setText("Restante das Receitas");
        labelTotalReceita.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelValorReceita.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelValorReceita.setForeground(new java.awt.Color(0, 126, 244));
        labelValorReceita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorReceita.setText("R$ 1420,00");

        javax.swing.GroupLayout panelTotalReceitaLayout = new javax.swing.GroupLayout(panelTotalReceita);
        panelTotalReceita.setLayout(panelTotalReceitaLayout);
        panelTotalReceitaLayout.setHorizontalGroup(
            panelTotalReceitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTotalReceita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
            .addComponent(labelValorReceita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTotalReceitaLayout.setVerticalGroup(
            panelTotalReceitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalReceitaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelTotalReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelValorReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelTotalPago.setBackground(new java.awt.Color(229, 229, 246));

        labelTotalPago.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelTotalPago.setForeground(new java.awt.Color(46, 43, 104));
        labelTotalPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalPago.setText("Total Pago");
        labelTotalPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelValorTotalPago.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelValorTotalPago.setForeground(new java.awt.Color(5, 137, 40));
        labelValorTotalPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorTotalPago.setText("R$ 60,00");

        javax.swing.GroupLayout panelTotalPagoLayout = new javax.swing.GroupLayout(panelTotalPago);
        panelTotalPago.setLayout(panelTotalPagoLayout);
        panelTotalPagoLayout.setHorizontalGroup(
            panelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTotalPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
            .addComponent(labelValorTotalPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTotalPagoLayout.setVerticalGroup(
            panelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalPagoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelValorTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRow2Layout = new javax.swing.GroupLayout(panelRow2);
        panelRow2.setLayout(panelRow2Layout);
        panelRow2Layout.setHorizontalGroup(
            panelRow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRow2Layout.createSequentialGroup()
                .addComponent(panelTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(panelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTotalReceita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRow2Layout.setVerticalGroup(
            panelRow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelTotalReceita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelRow3.setBackground(new java.awt.Color(255, 255, 255));

        panelRegiaoLinha.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(229, 229, 246));

        panelConfiguracaoLinhas.setBackground(new java.awt.Color(204, 204, 237));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(44, 41, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Semestre Atual");

        javax.swing.GroupLayout panelConfiguracaoLinhasLayout = new javax.swing.GroupLayout(panelConfiguracaoLinhas);
        panelConfiguracaoLinhas.setLayout(panelConfiguracaoLinhasLayout);
        panelConfiguracaoLinhasLayout.setHorizontalGroup(
            panelConfiguracaoLinhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );
        panelConfiguracaoLinhasLayout.setVerticalGroup(
            panelConfiguracaoLinhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(panelConfiguracaoLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConfiguracaoLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRegiaoLinhaLayout = new javax.swing.GroupLayout(panelRegiaoLinha);
        panelRegiaoLinha.setLayout(panelRegiaoLinhaLayout);
        panelRegiaoLinhaLayout.setHorizontalGroup(
            panelRegiaoLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRegiaoLinhaLayout.setVerticalGroup(
            panelRegiaoLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelRegiaoBarra.setBackground(new java.awt.Color(229, 229, 246));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(44, 41, 102));
        jLabel2.setText("Despesas");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(44, 41, 102));
        jLabel3.setText("Categorias com Maiores");

        javax.swing.GroupLayout panelRegiaoBarraLayout = new javax.swing.GroupLayout(panelRegiaoBarra);
        panelRegiaoBarra.setLayout(panelRegiaoBarraLayout);
        panelRegiaoBarraLayout.setHorizontalGroup(
            panelRegiaoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegiaoBarraLayout.createSequentialGroup()
                .addGroup(panelRegiaoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegiaoBarraLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel3))
                    .addGroup(panelRegiaoBarraLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        panelRegiaoBarraLayout.setVerticalGroup(
            panelRegiaoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegiaoBarraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(459, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRow3Layout = new javax.swing.GroupLayout(panelRow3);
        panelRow3.setLayout(panelRow3Layout);
        panelRow3Layout.setHorizontalGroup(
            panelRow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRow3Layout.createSequentialGroup()
                .addComponent(panelRegiaoLinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(panelRegiaoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRow3Layout.setVerticalGroup(
            panelRow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegiaoLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRegiaoBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addComponent(panelRow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelRow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelRow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelCentralizador.add(panelContent, new java.awt.GridBagConstraints());

        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1511, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAcessoRapido;
    private javax.swing.JLabel labelCumprimento;
    private javax.swing.JLabel labelFraseDia;
    private javax.swing.JLabel labelNovaDespesa;
    private javax.swing.JLabel labelNovaReceita;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelTotalPagar;
    private javax.swing.JLabel labelTotalPago;
    private javax.swing.JLabel labelTotalReceita;
    private javax.swing.JLabel labelValorReceita;
    private javax.swing.JLabel labelValorTotalPagar;
    private javax.swing.JLabel labelValorTotalPago;
    private javax.swing.JPanel panelBtnCadastrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelConfiguracaoLinhas;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRegiaoBarra;
    private javax.swing.JPanel panelRegiaoLinha;
    private javax.swing.JPanel panelRow1;
    private javax.swing.JPanel panelRow1Left;
    private javax.swing.JPanel panelRow2;
    private javax.swing.JPanel panelRow3;
    private javax.swing.JPanel panelRowRight;
    private javax.swing.JPanel panelTotalPagar;
    private javax.swing.JPanel panelTotalPago;
    private javax.swing.JPanel panelTotalReceita;
    // End of variables declaration//GEN-END:variables
}
