package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.controller.LancamentoController;
import com.mycompany.sistemaplanejago.dao.CategoriaDAO;
import com.mycompany.sistemaplanejago.dao.FrequenciaDAO;
import com.mycompany.sistemaplanejago.model.Categoria;
import com.mycompany.sistemaplanejago.model.Frequencia;
import com.mycompany.sistemaplanejago.model.Lancamento;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import java.util.List;


public class EditarReceita extends javax.swing.JDialog {

    private LancamentoController lancamentoController;
    private Lancamento lancamentoParaEdicao; 
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    public EditarReceita(java.awt.Frame parent, boolean modal, Lancamento lancamento) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.lancamentoParaEdicao = lancamento;
        this.lancamentoController = new LancamentoController();

        carregarComboBoxRepeticao(true); 
        carregarComboBoxCategoria(true);

        popularCamposParaEdicao(); 
        setModoEdicao(true); 
        buttonSalvar.setBackground(new Color(5, 137, 40)); 
    }

    public EditarReceita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.lancamentoParaEdicao = null; 
        this.lancamentoController = new LancamentoController();

        carregarComboBoxRepeticao(false); 
        carregarComboBoxCategoria(false);

        setModoEdicao(true); 
        buttonSalvar.setBackground(new Color(97, 90, 205)); // Cor azul para salvar nova receita
    }

    private void popularCamposParaEdicao() {
        if (lancamentoParaEdicao != null) {
            fieldDescricao.setText(lancamentoParaEdicao.getDescricao());
            fieldValor.setText(DECIMAL_FORMAT.format(lancamentoParaEdicao.getValor()));

            if (lancamentoParaEdicao.getDataCriacao() != null) {
                fieldDataCriacao.setText(lancamentoParaEdicao.getDataCriacao().format(DATE_FORMATTER));
            } else {
                fieldDataCriacao.setText("");
            }
            
            try {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.buscarCategoriaPorId(lancamentoParaEdicao.getCategoria());
                if (categoria != null) {
                    ComboBoxCategoria.setSelectedItem(categoria.getTitulo());
                } else {
                    ComboBoxCategoria.setSelectedItem("Selecione");
                }
            } catch (RuntimeException e) {
                System.err.println("EditarReceita.popularCamposParaEdicao: Erro ao buscar categoria por ID: " + e.getMessage());
                ComboBoxCategoria.setSelectedItem("Selecione");
            }

            try {
                FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
                Frequencia frequencia = frequenciaDAO.buscarFrequenciaPorId(lancamentoParaEdicao.getFrequencia());
                if (frequencia != null) {
                    ComboBoxRepeticao.setSelectedItem(frequencia.getTitulo());
                } else {
                    ComboBoxRepeticao.setSelectedItem("Selecione");
                }
            } catch (RuntimeException e) {
                System.err.println("EditarReceita.popularCamposParaEdicao: Erro ao buscar frequência por ID: " + e.getMessage());
                ComboBoxRepeticao.setSelectedItem("Selecione");
            }
        }
    }

    public void setModoEdicao(boolean editavel) {
        fieldDescricao.setEditable(editavel);
        fieldValor.setEditable(editavel);
        fieldDataCriacao.setEditable(editavel);
        ComboBoxCategoria.setEnabled(editavel);
        ComboBoxRepeticao.setEnabled(editavel);
        
        if (buttonSalvar != null) {
            buttonSalvar.setVisible(editavel);
        }
        if (panelSalvar1 != null) { 
            panelSalvar1.setVisible(editavel);
        }
    }

    private void carregarComboBoxRepeticao() { carregarComboBoxRepeticao(false); }
    private void carregarComboBoxRepeticao(boolean isViewingOrEditingMode) {
        try {
            FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
            List<Frequencia> frequencias = frequenciaDAO.listarTodasFrequencias();
            ComboBoxRepeticao.removeAllItems();
            if (!isViewingOrEditingMode) { ComboBoxRepeticao.addItem("Selecione"); }
            for (Frequencia freq : frequencias) { ComboBoxRepeticao.addItem(freq.getTitulo()); }
        } catch (RuntimeException e) { JOptionPane.showMessageDialog(this, "Erro ao carregar frequências: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }

    private void carregarComboBoxCategoria() { carregarComboBoxCategoria(false); }
    private void carregarComboBoxCategoria(boolean isViewingOrEditingMode) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.listarCategorias(2); // 2 = tipo Receita
            ComboBoxCategoria.removeAllItems();
            if (!isViewingOrEditingMode) { ComboBoxCategoria.addItem("Selecione"); }
            for (Categoria cat : categorias) { ComboBoxCategoria.addItem(cat.getTitulo()); }
        } catch (RuntimeException e) { JOptionPane.showMessageDialog(this, "Erro ao carregar categorias: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
    }

    // ID por Título 
    private int obterIdFrequenciaPorTitulo(String titulo) {
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        List<Frequencia> lista = frequenciaDAO.listarTodasFrequencias();
        for (Frequencia f : lista) { if (f.getTitulo().equalsIgnoreCase(titulo)) { return f.getId(); } }
        return -1;
    }

    private int obterIdCategoriaPorTitulo(String titulo, int tipoLancamento) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listarCategorias(tipoLancamento);
        for (Categoria cat : categorias) { if (cat.getTitulo().equalsIgnoreCase(titulo)) { return cat.getId(); } }
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
        panelSalvar1 = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        panelContent.setLayout(new java.awt.GridLayout(0, 1));

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(97, 90, 205));
        labelTitulo.setText("Editar Receita");
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

        panelSalvar1.setBackground(new java.awt.Color(255, 255, 255));

        buttonSalvar.setBackground(new java.awt.Color(5, 137, 40));
        buttonSalvar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        buttonSalvar.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalvar.setText("Salvar");
        buttonSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonSalvarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSalvarMouseExited(evt);
            }
        });
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSalvar1Layout = new javax.swing.GroupLayout(panelSalvar1);
        panelSalvar1.setLayout(panelSalvar1Layout);
        panelSalvar1Layout.setHorizontalGroup(
            panelSalvar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSalvar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSalvar1Layout.setVerticalGroup(
            panelSalvar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSalvar1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(buttonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelContent.add(panelSalvar1);

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

    private void buttonSalvarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSalvarMouseEntered
        // TODO add your handling code here:
        buttonSalvar.setBackground(new Color(27, 189, 70));
    }//GEN-LAST:event_buttonSalvarMouseEntered

    private void buttonSalvarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSalvarMouseExited
        // TODO add your handling code here:
        buttonSalvar.setBackground(new Color(4, 137, 40));
    }//GEN-LAST:event_buttonSalvarMouseExited

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        // TODO add your handling code here:
        if (lancamentoParaEdicao.getId() <= 0) {
            JOptionPane.showMessageDialog(this, "Erro: ID de lançamento inválido para atualização.", "Erro de Edição", JOptionPane.ERROR_MESSAGE);
            return; // Sai da função
        }

        boolean sucesso = false; // Inicializa 'sucesso' como false por padrão

        try {
            String descricao = fieldDescricao.getText();
            if (descricao.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "A Descrição da Receita não pode ser vazia.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigDecimal valor = new BigDecimal(fieldValor.getText().replace(",", "."));
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "O Valor da Receita deve ser positivo.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate dataCriacao = LocalDate.parse(fieldDataCriacao.getText(), DATE_FORMATTER);
            if (dataCriacao == null) {
                JOptionPane.showMessageDialog(this, "A Data de Criação não pode ser vazia.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String freqTitulo = (String) ComboBoxRepeticao.getSelectedItem();
            int frequenciaId = obterIdFrequenciaPorTitulo(freqTitulo);
            if (frequenciaId == -1 || "Selecione".equals(freqTitulo)) {
                JOptionPane.showMessageDialog(this, "Selecione uma Frequência válida.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String catTitulo = (String) ComboBoxCategoria.getSelectedItem();
            int categoriaId = obterIdCategoriaPorTitulo(catTitulo, 2); // Passa 2 para tipo Receita
            if (categoriaId == -1 || "Selecione".equals(catTitulo)) {
                JOptionPane.showMessageDialog(this, "Selecione uma Categoria válida.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sucesso = lancamentoController.atualizarReceita(
                                lancamentoParaEdicao.getId(), 
                                descricao,
                                valor,
                                dataCriacao,
                                frequenciaId,
                                categoriaId
                            );

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Receita atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                if (getParent() instanceof TelaLancamento) {
                    ((TelaLancamento) getParent()).carregarLancamentosNaTabela();
                } 
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao atualizar receita. Verifique o console para mais detalhes.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Use apenas números e vírgula para decimais (ex: 123.45).", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); 
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use DD/MM/AAAA (ex: 31/12/2023).", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro inesperado em buttonSalvarActionPerformed: " + ex.getMessage());
            ex.printStackTrace(); 
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(EditarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarReceita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarReceita dialog = new EditarReceita(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buttonSalvar;
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
    private javax.swing.JPanel panelSalvar1;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel panelValor;
    // End of variables declaration//GEN-END:variables
}
