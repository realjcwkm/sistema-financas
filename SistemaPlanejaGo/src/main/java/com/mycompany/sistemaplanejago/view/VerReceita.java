package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.LancamentoController;
import com.mycompany.sistemaplanejago.dao.CategoriaDAO;
import com.mycompany.sistemaplanejago.dao.FrequenciaDAO;
import com.mycompany.sistemaplanejago.model.Categoria;
import com.mycompany.sistemaplanejago.model.Frequencia;
import com.mycompany.sistemaplanejago.model.Lancamento; // IMPORTANTE: Importar Lancamento
import java.awt.Color;
import java.text.DecimalFormat; // Para formatar o valor
import java.time.format.DateTimeFormatter; // Para formatar datas
import javax.swing.JOptionPane;
import java.util.List;

public class VerReceita extends javax.swing.JDialog {

    private LancamentoController lancamentoController;
    private Lancamento lancamentoAtual; 
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    // Receita
    public VerReceita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        lancamentoController = new LancamentoController();

        carregarComboBoxRepeticao(false); 
        carregarComboBoxCategoria(false); 

    }

    // visualizar um lançamento existente
    public VerReceita(java.awt.Frame parent, boolean modal, Lancamento lancamento) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.lancamentoAtual = lancamento; 
        lancamentoController = new LancamentoController();

        carregarComboBoxRepeticao(true);
        carregarComboBoxCategoria(true);

        popularCamposComLancamento(); // Preenche os campos com os dados do lançamento

        // Desabilita os campos pro usuario só ver e não alterar nada
        setModoVisualizacao(); 
    }

    // Método para popular os campos com os dados do lançamento
    private void popularCamposComLancamento() {
        if (lancamentoAtual != null) {
            fieldDescricao.setText(lancamentoAtual.getDescricao());
            fieldValor.setText(DECIMAL_FORMAT.format(lancamentoAtual.getValor()));

            // Datas
            if (lancamentoAtual.getDataCriacao() != null) {
                fieldDataCriacao.setText(lancamentoAtual.getDataCriacao().format(DATE_FORMATTER));
            } else {
                fieldDataCriacao.setText("");
            }

            // Categorias
            try {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                
                Categoria categoria = categoriaDAO.buscarCategoriaPorId(lancamentoAtual.getCategoria());
                if (categoria != null) {
                    ComboBoxCategoria.setSelectedItem(categoria.getTitulo());
                } else {
                    ComboBoxCategoria.setSelectedItem("Selecione");
                }
            } catch (RuntimeException e) {
                System.err.println("Erro ao buscar categoria por ID em VerReceita: " + e.getMessage());
                ComboBoxCategoria.setSelectedItem("Selecione"); 
            }

            // Frequência
            try {
                FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
                Frequencia frequencia = frequenciaDAO.buscarFrequenciaPorId(lancamentoAtual.getFrequencia());
                if (frequencia != null) {
                    ComboBoxRepeticao.setSelectedItem(frequencia.getTitulo());
                } else {
                    ComboBoxRepeticao.setSelectedItem("Selecione");
                }
            } catch (RuntimeException e) {
                System.err.println("Erro ao buscar frequência por ID em VerReceita: " + e.getMessage());
                ComboBoxRepeticao.setSelectedItem("Selecione"); 
            }
        }
    }

    // Desabilita a edição dos campos
    public void setModoVisualizacao() {
        fieldDescricao.setEditable(false);
        fieldValor.setEditable(false);
        fieldDataCriacao.setEditable(false);
        ComboBoxCategoria.setEnabled(false);
        ComboBoxRepeticao.setEnabled(false);
    }


    private void carregarComboBoxRepeticao() { 
        carregarComboBoxRepeticao(false);
    }

    private void carregarComboBoxRepeticao(boolean isViewingMode) {
        try {
            FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
            List<Frequencia> frequencias = frequenciaDAO.listarTodasFrequencias();

            ComboBoxRepeticao.removeAllItems();
            if (!isViewingMode) { 
                ComboBoxRepeticao.addItem("Selecione");
            }

            for (Frequencia freq : frequencias) {
                ComboBoxRepeticao.addItem(freq.getTitulo());
            }

        } catch (RuntimeException e) {
            System.err.println("Erro ao carregar dados de frequência: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Não foi possível carregar as frequências do banco de dados.\nDetalhes: " + e.getMessage(),
                    "Erro de Carregamento",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarComboBoxCategoria() { 
        carregarComboBoxCategoria(false);
    }

    private void carregarComboBoxCategoria(boolean isViewingMode) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.listarCategorias(2); 

            ComboBoxCategoria.removeAllItems();
            if (!isViewingMode) { 
                ComboBoxCategoria.addItem("Selecione");
            }

            for (Categoria cat : categorias) {
                ComboBoxCategoria.addItem(cat.getTitulo());
            }

        } catch (RuntimeException e) {
            System.err.println("Erro ao carregar dados de categoria: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Não foi possível carregar as categorias de receitas do banco de dados.\nDetalhes: " + e.getMessage(),
                    "Erro de Carregamento",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obterIdFrequenciaPorTitulo(String titulo) {
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        List<Frequencia> lista = frequenciaDAO.listarTodasFrequencias();

        for (Frequencia f : lista) {
            if (f.getTitulo().equalsIgnoreCase(titulo)) {
                return f.getId();
            }
        }
        return -1;
    }

    private int obterIdCategoriaPorTitulo(String titulo, int tipoLancamento) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listarCategorias(tipoLancamento);

        for (Categoria cat : categorias) {
            if (cat.getTitulo().equalsIgnoreCase(titulo)) {
                return cat.getId();
            }
        }
        return -1;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelCentralizador = new javax.swing.JPanel();
        panelContent = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelDescricao = new javax.swing.JPanel();
        labelDescricao = new javax.swing.JLabel();
        fieldDescricao = new javax.swing.JTextField();
        panelValor = new javax.swing.JPanel();
        labelValor = new javax.swing.JLabel();
        fieldValor = new javax.swing.JTextField();
        labelDataCriacao = new javax.swing.JLabel();
        fieldDataCriacao = new javax.swing.JTextField();
        panelCategoria = new javax.swing.JPanel();
        labelCategoria = new javax.swing.JLabel();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        panelRepeticao = new javax.swing.JPanel();
        labelRepeticao = new javax.swing.JLabel();
        ComboBoxRepeticao = new javax.swing.JComboBox<>();
        panelSalvar = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        panelContent.setLayout(new java.awt.GridLayout(0, 1));

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(97, 90, 205));
        labelTitulo.setText("Visualizar Receita");
        panelTitulo.add(labelTitulo);

        panelContent.add(panelTitulo);

        panelDescricao.setBackground(new java.awt.Color(255, 255, 255));

        labelDescricao.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelDescricao.setForeground(new java.awt.Color(19, 16, 71));
        labelDescricao.setText("Descrição");

        fieldDescricao.setBackground(new java.awt.Color(255, 255, 255));
        fieldDescricao.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDescricaoLayout = new javax.swing.GroupLayout(panelDescricao);
        panelDescricao.setLayout(panelDescricaoLayout);
        panelDescricaoLayout.setHorizontalGroup(
            panelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescricaoLayout.createSequentialGroup()
                .addGroup(panelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldDescricao)
                    .addGroup(panelDescricaoLayout.createSequentialGroup()
                        .addComponent(labelDescricao)
                        .addGap(0, 402, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDescricaoLayout.setVerticalGroup(
            panelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescricaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDescricao)
                .addContainerGap())
        );

        panelContent.add(panelDescricao);

        panelValor.setBackground(new java.awt.Color(255, 255, 255));

        labelValor.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelValor.setForeground(new java.awt.Color(19, 16, 71));
        labelValor.setText("Valor");

        fieldValor.setBackground(new java.awt.Color(255, 255, 255));
        fieldValor.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldValor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldValorActionPerformed(evt);
            }
        });

        labelDataCriacao.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelDataCriacao.setForeground(new java.awt.Color(19, 16, 71));
        labelDataCriacao.setText("Data da Criação");

        fieldDataCriacao.setBackground(new java.awt.Color(255, 255, 255));
        fieldDataCriacao.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldDataCriacao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldDataCriacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDataCriacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelValorLayout = new javax.swing.GroupLayout(panelValor);
        panelValor.setLayout(panelValorLayout);
        panelValorLayout.setHorizontalGroup(
            panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorLayout.createSequentialGroup()
                .addGroup(panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelValorLayout.createSequentialGroup()
                        .addComponent(fieldDataCriacao, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelValorLayout.setVerticalGroup(
            panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValor)
                    .addComponent(labelDataCriacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldValor, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(fieldDataCriacao, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelContent.add(panelValor);

        panelCategoria.setBackground(new java.awt.Color(255, 255, 255));
        panelCategoria.setPreferredSize(new java.awt.Dimension(418, 95));

        labelCategoria.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelCategoria.setForeground(new java.awt.Color(19, 16, 71));
        labelCategoria.setText("Categoria");

        ComboBoxCategoria.setBackground(new java.awt.Color(255, 255, 255));
        ComboBoxCategoria.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        javax.swing.GroupLayout panelCategoriaLayout = new javax.swing.GroupLayout(panelCategoria);
        panelCategoria.setLayout(panelCategoriaLayout);
        panelCategoriaLayout.setHorizontalGroup(
            panelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoriaLayout.createSequentialGroup()
                .addGroup(panelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCategoriaLayout.createSequentialGroup()
                        .addComponent(labelCategoria)
                        .addGap(0, 403, Short.MAX_VALUE))
                    .addComponent(ComboBoxCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCategoriaLayout.setVerticalGroup(
            panelCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoriaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelContent.add(panelCategoria);

        panelRepeticao.setBackground(new java.awt.Color(255, 255, 255));
        panelRepeticao.setPreferredSize(new java.awt.Dimension(418, 95));

        labelRepeticao.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelRepeticao.setForeground(new java.awt.Color(19, 16, 71));
        labelRepeticao.setText("Repetição");

        ComboBoxRepeticao.setBackground(new java.awt.Color(255, 255, 255));
        ComboBoxRepeticao.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ComboBoxRepeticao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        javax.swing.GroupLayout panelRepeticaoLayout = new javax.swing.GroupLayout(panelRepeticao);
        panelRepeticao.setLayout(panelRepeticaoLayout);
        panelRepeticaoLayout.setHorizontalGroup(
            panelRepeticaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepeticaoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelRepeticaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxRepeticao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRepeticaoLayout.createSequentialGroup()
                        .addComponent(labelRepeticao)
                        .addGap(0, 399, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRepeticaoLayout.setVerticalGroup(
            panelRepeticaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepeticaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelRepeticao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxRepeticao)
                .addContainerGap())
        );

        panelContent.add(panelRepeticao);

        panelSalvar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelSalvarLayout = new javax.swing.GroupLayout(panelSalvar);
        panelSalvar.setLayout(panelSalvarLayout);
        panelSalvarLayout.setHorizontalGroup(
            panelSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        panelSalvarLayout.setVerticalGroup(
            panelSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        panelContent.add(panelSalvar);

        panelCentralizador.add(panelContent, new java.awt.GridBagConstraints());

        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDescricaoActionPerformed

    private void fieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldValorActionPerformed

    private void fieldDataCriacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDataCriacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDataCriacaoActionPerformed

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
            java.util.logging.Logger.getLogger(VerReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VerReceita dialog = new VerReceita(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JComboBox<String> ComboBoxRepeticao;
    private javax.swing.JTextField fieldDataCriacao;
    private javax.swing.JTextField fieldDescricao;
    private javax.swing.JTextField fieldValor;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelDataCriacao;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelRepeticao;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel panelCategoria;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelDescricao;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRepeticao;
    private javax.swing.JPanel panelSalvar;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelValor;
    // End of variables declaration//GEN-END:variables
}
