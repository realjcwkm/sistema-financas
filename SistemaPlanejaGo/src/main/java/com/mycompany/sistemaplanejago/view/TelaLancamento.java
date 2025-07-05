package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionCellEditor;
import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionCellRender;
import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionEvent;
import com.mycompany.sistemaplanejago.componentes.SwitchButton.SwitchButtonCellEditor; 
import com.mycompany.sistemaplanejago.componentes.SwitchButton.SwitchButtonCellRenderer; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn; 


public class TelaLancamento extends javax.swing.JFrame {
    
    public TelaLancamento() {
        initComponents(); 
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        // o cabeçalho da tabela 
        JTableHeader cabecalho = TabelaLancamento.getTableHeader();
        cabecalho.setFont(new Font("Roboto", Font.BOLD, 24)); 
        cabecalho.setForeground(new Color(19, 16, 71)); 
        cabecalho.setBackground(new Color(248, 248, 255));
        cabecalho.setOpaque(true);

        // Depois vai vir pelo banco 
        final DefaultTableModel customTableModel = new DefaultTableModel( 
                new Object[][]{
                    {false, "Despesa", "Conta de Luz", "Casa", 150.00, null}, 
                    {true, "Receita", "Salário", "Trabalho", 3000.00, null}, 
                    {false, "Despesa", "Aluguel", "Moradia", 750.00, null}, 
                    {true, "Receita", "Venda Online", "Extra", 120.50, null}
                },
                new String[]{"", "Tipo", "Descrição", "Categoria", "Valor", "Opções"}
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) { return Boolean.class; }
                if (columnIndex == getColumnCount() - 1) { return Object.class; }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                String tipo = (String) getValueAt(row, 1); 
                int lastColumnIndex = getColumnCount() - 1; 

                if (column == 0) { return "Despesa".equalsIgnoreCase(tipo); }
                if (column == lastColumnIndex) { return true; }
                return false;
            }
        };

        TabelaLancamento.setModel(customTableModel);

        // Configuração da coluna do status
        TableColumn pagoColumn = TabelaLancamento.getColumnModel().getColumn(0);
        pagoColumn.setCellRenderer(new SwitchButtonCellRenderer());
        pagoColumn.setCellEditor(new SwitchButtonCellEditor());
        
        pagoColumn.setPreferredWidth(150); 
        pagoColumn.setMaxWidth(200);       
        pagoColumn.setMinWidth(100);       

        TabelaLancamento.setRowHeight(50); 

        // Configuração da coluna Opções
        int lastColumnIndex = TabelaLancamento.getColumnCount() - 1; 
        TableColumn acoesColumn = TabelaLancamento.getColumnModel().getColumn(lastColumnIndex);
        
        // Depois fazer as operações conforme o design do figma
        final TableActionEvent event = new TableActionEvent() { 
            @Override public void onEdit(int row) { JOptionPane.showMessageDialog(TelaLancamento.this, "Editar linha: " + (row + 1)); }
            @Override public void onDelete(int row) { 
                int confirm = JOptionPane.showConfirmDialog(TelaLancamento.this, "Tem certeza que deseja excluir a linha " + (row + 1) + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) { customTableModel.removeRow(row); }
            }
            @Override public void onView(int row) { JOptionPane.showMessageDialog(TelaLancamento.this, "Visualizar detalhes da linha: " + (row + 1)); }
        };

        acoesColumn.setCellRenderer(new TableActionCellRender()); 
        acoesColumn.setCellEditor(new TableActionCellEditor(event)); 

        // Aumenta a largura da coluna opções
        acoesColumn.setPreferredWidth(140); 
        acoesColumn.setMaxWidth(160);       
        acoesColumn.setMinWidth(120);       
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
        PanelRow1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaLancamento = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

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

        PanelRow1.setBackground(new java.awt.Color(255, 255, 255));

        TabelaLancamento.setBackground(new java.awt.Color(255, 255, 255));
        TabelaLancamento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TabelaLancamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "Tipo", "Descrição", "Categoria", "Valor", "Opções"
            }
        ));
        jScrollPane2.setViewportView(TabelaLancamento);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(97, 90, 205));
        jButton2.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+");

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(97, 90, 205));
        labelTitulo.setText("Lançamento");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setBackground(new java.awt.Color(229, 229, 246));
        jTextField1.setText("jTextField1");

        jButton1.setBackground(new java.awt.Color(97, 90, 205));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelRow1Layout = new javax.swing.GroupLayout(PanelRow1);
        PanelRow1.setLayout(PanelRow1Layout);
        PanelRow1Layout.setHorizontalGroup(
            PanelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelRow1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelRow1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelRow1Layout.setVerticalGroup(
            PanelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRow1Layout.createSequentialGroup()
                .addGroup(PanelRow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelRow1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCentralizador.add(PanelRow1, new java.awt.GridBagConstraints());

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
            java.util.logging.Logger.getLogger(TelaLancamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLancamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelRow1;
    private javax.swing.JTable TabelaLancamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBtnCadastrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
