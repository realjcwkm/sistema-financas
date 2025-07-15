package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionCellEditor;
import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionCellRender;
import com.mycompany.sistemaplanejago.componentes.ActionButtons.TableActionEvent;
import com.mycompany.sistemaplanejago.componentes.SwitchButton.SwitchButtonCellEditor;
import com.mycompany.sistemaplanejago.componentes.SwitchButton.SwitchButtonCellRenderer;

import com.mycompany.sistemaplanejago.controller.LancamentoController;
import com.mycompany.sistemaplanejago.model.Lancamento; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel; 
import javax.swing.table.JTableHeader; 
import javax.swing.table.TableColumn; 
import javax.swing.SwingWorker; 

public class TelaLancamento extends javax.swing.JFrame {

    private LancamentoController lancamentoController;
    private List<Lancamento> lancamentosCarregados;

    public TelaLancamento() {
        
        initComponents();

        panelAddLancamento.setVisible(false); 
        
        // Set the initial placeholder text
        ((JTextField) labelBarraPesquisa).setText("Pesquise aqui"); // Cast if labelBarraPesquisa is not directly JTextField

        // Add FocusListener directly to the existing JTextField
        ((JTextField) labelBarraPesquisa).addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTextField source = (JTextField) evt.getSource();
                if (source.getText().equals("Pesquise aqui")) {
                    source.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTextField source = (JTextField) evt.getSource();
                if (source.getText().isEmpty()) {
                    source.setText("Pesquise aqui");
                }
            }
        });
        
        //Hover Links
        labelLancamentos.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                labelLancamentos.setForeground(new Color(255, 160, 0)); // Laranja
            }

            public void mouseExited(MouseEvent evt) {
                labelLancamentos.setForeground(Color.WHITE); // Branca
            }
        });
        
        labelRelatorios.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                labelRelatorios.setForeground(new Color(255, 160, 0)); // Laranja
            }

            public void mouseExited(MouseEvent evt) {
                labelRelatorios.setForeground(Color.WHITE); // Branca
            }
        });
        
        labelSair.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                labelSair.setForeground(new Color(255, 160, 0)); // Laranja
            }

            public void mouseExited(MouseEvent evt) {
                labelSair.setForeground(Color.WHITE); // Branca
            }
        });
        
        buttonPesquisa.addActionListener(e -> {
            realizarPesquisa(); // Chama o método de pesquisa
        });
      

        this.setExtendedState(Frame.MAXIMIZED_BOTH); // Maximiza a janela ao iniciar

        lancamentoController = new LancamentoController(); // Inicializa o Controller
        lancamentosCarregados = new ArrayList<>(); 

        // Cabeçalho da Tabela 
        JTableHeader cabecalho = TabelaLancamento.getTableHeader();
        cabecalho.setFont(new Font("Roboto", Font.BOLD, 24));
        cabecalho.setForeground(new Color(19, 16, 71)); 
        cabecalho.setBackground(new Color(248, 248, 255)); 
        cabecalho.setOpaque(true); 

        // Modelo da Tabela 
        final DefaultTableModel customTableModel = new DefaultTableModel(
                new Object[][]{}, 
                new String[]{"", "Tipo", "Descrição", "Categoria", "Valor", "Opções"} //A primeira é o switch button
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) { return Boolean.class; } // status pago/não pago)
                if (columnIndex == getColumnCount() - 1) { return Object.class; } // botões de ação Editar / Excluir / Visualizar
                return super.getColumnClass(columnIndex); 
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Controla quais células são editáveis.
                String tipo = (String) getValueAt(row, 1); // Pega o tipo de lançamento da coluna "Tipo"
                int lastColumnIndex = getColumnCount() - 1;

                if (column == 0) { 
                    return "Despesa".equalsIgnoreCase(tipo); // Editável APENAS se for uma Despesa
                }
                if (column == lastColumnIndex) { 
                    return true; // Editável para permitir os botões de ação
                }
                return false; 
            }
        };
        
        TabelaLancamento.setModel(customTableModel); 

        TableColumn pagoColumn = TabelaLancamento.getColumnModel().getColumn(0);
        pagoColumn.setCellRenderer(new SwitchButtonCellRenderer()); // Renderiza o SwitchButton
        pagoColumn.setCellEditor(new SwitchButtonCellEditor()); // Permite interagir com o SwitchButton
        pagoColumn.setPreferredWidth(150); // Configuracoes do tamanho
        pagoColumn.setMaxWidth(200);      
        pagoColumn.setMinWidth(100);      

        TabelaLancamento.setRowHeight(50); // altura de todas as linhas da tabela

        // Botões de Ação
        int lastColumnIndex = TabelaLancamento.getColumnCount() - 1;
        TableColumn acoesColumn = TabelaLancamento.getColumnModel().getColumn(lastColumnIndex);

        final TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                if (row >= 0 && row < lancamentosCarregados.size()) {
                    Lancamento lancamentoEdit = lancamentosCarregados.get(row); 
                    if (lancamentoEdit.getTipo() == 1) { // Se for uma Despesa 
                        EditarDespesa editarDespesaDialog = new EditarDespesa(TelaLancamento.this, true, lancamentoEdit);
                        editarDespesaDialog.setVisible(true); 
                    } else if (lancamentoEdit.getTipo() == 2) { // Se for uma Receita 
                        EditarReceita editarReceitaDialog = new EditarReceita(TelaLancamento.this, true, lancamentoEdit);
                        editarReceitaDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(TelaLancamento.this, "Tipo de lançamento desconhecido para edição.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void onDelete(int row) {
                int confirm = JOptionPane.showConfirmDialog(TelaLancamento.this, "Tem certeza que deseja excluir esse lançamento ", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (row >= 0 && row < lancamentosCarregados.size()) {
                        Lancamento lancamentoDelete = lancamentosCarregados.get(row); 
                        boolean sucessoExclusao = lancamentoController.excluirLancamento(lancamentoDelete.getId());
                        if (sucessoExclusao) {
                            JOptionPane.showMessageDialog(TelaLancamento.this, "Lançamento excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            carregarLancamentosNaTabela(); 
                        } else {
                            JOptionPane.showMessageDialog(TelaLancamento.this, "Falha ao excluir lançamento. Verifique o console para mais detalhes.", "Erro de Exclusão", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            @Override
            public void onView(int row) {
                if (row >= 0 && row < lancamentosCarregados.size()) {
                    Lancamento lancamentoEdit = lancamentosCarregados.get(row); 
                    if (lancamentoEdit.getTipo() == 1) { // Se for uma Despesa 
                        EditarDespesa editarDespesaDialog = new EditarDespesa(TelaLancamento.this, true, lancamentoEdit);
                        editarDespesaDialog.setVisible(true);
                    } else if (lancamentoEdit.getTipo() == 2) { // Se for uma Receita 
                        EditarReceita editarReceitaDialog = new EditarReceita(TelaLancamento.this, true, lancamentoEdit);
                        editarReceitaDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(TelaLancamento.this, "Tipo de lançamento desconhecido para edição.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        acoesColumn.setCellRenderer(new TableActionCellRender()); // Renderiza os botões
        acoesColumn.setCellEditor(new TableActionCellEditor(event)); // Adiciona a funcionalidade de clique

        acoesColumn.setPreferredWidth(140); // Tamanho
        acoesColumn.setMaxWidth(160);      
        acoesColumn.setMinWidth(120);     

        carregarLancamentosNaTabela();

        // coluna de Status 
        customTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 0) {
                    int row = e.getFirstRow();
                    if (row >= 0 && row < lancamentosCarregados.size()) {
                        boolean novoStatus = (boolean) customTableModel.getValueAt(row, 0); // Pega o novo estado do SwitchButton

                        Lancamento lancamento = lancamentosCarregados.get(row);
                        int idLancamento = lancamento.getId();
                        String tipoLancamento = (String) customTableModel.getValueAt(row, 1);

                        // Atualiza o status pago apenas se o lançamento for uma Despesa
                        if ("Despesa".equalsIgnoreCase(tipoLancamento)) {
                            new SwingWorker<Boolean, Void>() {
                                @Override
                                protected Boolean doInBackground() throws Exception {
                                    // Chama o Controller para atualizar o status no banco de dados
                                    return lancamentoController.atualizarStatusLancamento(idLancamento, novoStatus);
                                }
                                @Override
                                protected void done() {
                                    try {
                                        boolean sucesso = get(); 
                                        if (sucesso) {
                                            lancamento.setStatusPago(novoStatus); // Atualiza o objeto na lista local em memória
                                        } else {
                                            // Se a atualização falhou no DB, reverte o estado visual do SwitchButton na UI
                                            customTableModel.setValueAt(!novoStatus, row, 0);
                                            JOptionPane.showMessageDialog(TelaLancamento.this, "Falha ao atualizar status da despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (Exception ex) {
                                        customTableModel.setValueAt(!novoStatus, row, 0);
                                        JOptionPane.showMessageDialog(TelaLancamento.this, "Erro inesperado ao atualizar status: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                        ex.printStackTrace();
                                    }
                                }
                            }.execute(); 
                        } else {
                            customTableModel.setValueAt(!novoStatus, row, 0);
                            JOptionPane.showMessageDialog(TelaLancamento.this, "O status de uma Receita não pode ser alterado por este botão.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    public void carregarLancamentosNaTabela() { 
        final DefaultTableModel model = (DefaultTableModel) TabelaLancamento.getModel();
        model.setRowCount(0); 
        
        lancamentosCarregados.clear(); 

        new SwingWorker<List<Lancamento>, Void>() { 
            @Override
            protected List<Lancamento> doInBackground() throws Exception {
                return lancamentoController.listarLancamentos(); // Chama o controlador para buscar os dados
            }

            @Override
            protected void done() {
                try {
                    List<Lancamento> lancamentos = get(); 

                    if (lancamentos.isEmpty()) {
                        System.out.println("Nenhum lançamento encontrado para adicionar à tabela.");
                    } else {
                        for (Lancamento lancamento : lancamentos) {
                            lancamentosCarregados.add(lancamento); 

                            String tipoNome = lancamentoController.getNomeTipoLancamento(lancamento.getTipo());
                            String categoriaNome = lancamentoController.getNomeCategoria(lancamento.getCategoria());
                            Object statusDisplay = (lancamento.getTipo() == 1) ? lancamento.isStatusPago() : null;

                            model.addRow(new Object[]{
                                statusDisplay,
                                tipoNome,
                                lancamento.getDescricao(),
                                categoriaNome,
                                lancamento.getValor(),
                                null 
                            });
                        }
                    }
                    model.fireTableDataChanged(); 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(TelaLancamento.this, "Erro ao carregar lançamentos: " + e.getMessage(), "Erro de Carregamento", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    
    private void realizarPesquisa() {
        final DefaultTableModel model = (DefaultTableModel) TabelaLancamento.getModel();
        model.setRowCount(0);

        lancamentosCarregados.clear(); // Limpa a lista local 

        String termoPesquisa = ((JTextField) labelBarraPesquisa).getText().trim();

        if (termoPesquisa.equals("Pesquise aqui") || termoPesquisa.isEmpty()) {
            carregarLancamentosNaTabela(); 
            return; 
        }

        new SwingWorker<List<Lancamento>, Void>() {
            @Override
            protected List<Lancamento> doInBackground() throws Exception {
                return lancamentoController.pesquisarLancamentos(termoPesquisa);
            }

            @Override
            protected void done() {
                try {
                    List<Lancamento> resultadosPesquisa = get(); 

                    if (resultadosPesquisa.isEmpty()) {
                        JOptionPane.showMessageDialog(TelaLancamento.this, "Nenhum lançamento encontrado para o termo: \"" + termoPesquisa + "\"", "Pesquisa Vazia", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        for (Lancamento lancamento : resultadosPesquisa) {
                            lancamentosCarregados.add(lancamento); 

                            String tipoNome = lancamentoController.getNomeTipoLancamento(lancamento.getTipo());
                            String categoriaNome = lancamentoController.getNomeCategoria(lancamento.getCategoria());
                            Object statusDisplay = (lancamento.getTipo() == 1) ? lancamento.isStatusPago() : null;

                            model.addRow(new Object[]{
                                statusDisplay,
                                tipoNome,
                                lancamento.getDescricao(),
                                categoriaNome,
                                lancamento.getValor(),
                                null 
                            });
                        }
                    }
                    model.fireTableDataChanged(); 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(TelaLancamento.this, "Erro ao realizar pesquisa: " + e.getMessage(), "Erro de Pesquisa", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    
    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelCentralizador = new javax.swing.JPanel();
        PanelRow1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaLancamento = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        buttonAddLancamento = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        panelAddLancamento = new javax.swing.JPanel();
        buttonNovaDespesa = new javax.swing.JButton();
        buttonNovaReceita = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labelBarraPesquisa = new javax.swing.JTextField();
        buttonPesquisa = new javax.swing.JButton();
        panelNavBar = new javax.swing.JPanel();
        labelPlanejaGo = new javax.swing.JLabel();
        panelBtnEntrar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelRelatorios = new javax.swing.JLabel();
        labelLancamentos = new javax.swing.JLabel();
        labelSair = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelCentralizador.setBackground(new java.awt.Color(255, 255, 255));
        panelCentralizador.setLayout(new java.awt.GridBagLayout());

        PanelRow1.setBackground(new java.awt.Color(255, 255, 255));

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

        buttonAddLancamento.setBackground(new java.awt.Color(97, 90, 205));
        buttonAddLancamento.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        buttonAddLancamento.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddLancamento.setText("+");
        buttonAddLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddLancamentoActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(97, 90, 205));
        labelTitulo.setText("Lançamento");

        panelAddLancamento.setBackground(new java.awt.Color(255, 255, 255));

        buttonNovaDespesa.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        buttonNovaDespesa.setForeground(new java.awt.Color(44, 41, 102));
        buttonNovaDespesa.setText("Nova Despesa");
        buttonNovaDespesa.setToolTipText("");
        buttonNovaDespesa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonNovaDespesa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonNovaDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovaDespesaActionPerformed(evt);
            }
        });

        buttonNovaReceita.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        buttonNovaReceita.setForeground(new java.awt.Color(44, 41, 102));
        buttonNovaReceita.setText("Nova Receita");
        buttonNovaReceita.setToolTipText("");
        buttonNovaReceita.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonNovaReceita.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonNovaReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovaReceitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAddLancamentoLayout = new javax.swing.GroupLayout(panelAddLancamento);
        panelAddLancamento.setLayout(panelAddLancamentoLayout);
        panelAddLancamentoLayout.setHorizontalGroup(
            panelAddLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonNovaReceita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonNovaDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        panelAddLancamentoLayout.setVerticalGroup(
            panelAddLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLancamentoLayout.createSequentialGroup()
                .addComponent(buttonNovaDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNovaReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAddLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAddLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAddLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelAddLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        labelBarraPesquisa.setBackground(new java.awt.Color(229, 229, 246));
        labelBarraPesquisa.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        buttonPesquisa.setBackground(new java.awt.Color(97, 90, 205));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBarraPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(buttonPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCentralizador.add(PanelRow1, new java.awt.GridBagConstraints());

        panelPrincipal.add(panelCentralizador, java.awt.BorderLayout.CENTER);

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

        panelBtnEntrar.setBackground(new java.awt.Color(44, 41, 102));
        panelBtnEntrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 30));
        panelNavBar.add(panelBtnEntrar, java.awt.BorderLayout.LINE_END);

        jPanel1.setBackground(new java.awt.Color(44, 41, 102));

        jPanel3.setBackground(new java.awt.Color(44, 41, 102));

        labelRelatorios.setBackground(new java.awt.Color(44, 41, 102));
        labelRelatorios.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        labelRelatorios.setText("Relatórios");
        labelRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRelatoriosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRelatorios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelLancamentos.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelLancamentos.setForeground(new java.awt.Color(255, 255, 255));
        labelLancamentos.setText("Lançamentos");
        labelLancamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLancamentosMouseClicked(evt);
            }
        });

        labelSair.setBackground(new java.awt.Color(44, 41, 102));
        labelSair.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        labelSair.setForeground(new java.awt.Color(255, 255, 255));
        labelSair.setText("Sair");
        labelSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelLancamentos)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 907, Short.MAX_VALUE)
                .addComponent(labelSair)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLancamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelNavBar.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelPrincipal.add(panelNavBar, java.awt.BorderLayout.NORTH);

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

    private void buttonNovaReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovaReceitaActionPerformed
        ReceitaForm Receitaformulario = new ReceitaForm(this, true);
        Receitaformulario.setVisible(true);
    }//GEN-LAST:event_buttonNovaReceitaActionPerformed

    private void buttonAddLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddLancamentoActionPerformed
        if (panelAddLancamento.isVisible()) {
            panelAddLancamento.setVisible(false); 
        } else {
            panelAddLancamento.setVisible(true);
        }
    }//GEN-LAST:event_buttonAddLancamentoActionPerformed

    private void buttonNovaDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovaDespesaActionPerformed
        DespesaForm Despesaformulario = new DespesaForm(this, true);
        Despesaformulario.setVisible(true);
    }//GEN-LAST:event_buttonNovaDespesaActionPerformed

    private void labelPlanejaGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlanejaGoMouseClicked
        // TODO add your handling code here:
        new TelaHome().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelPlanejaGoMouseClicked

    private void labelRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRelatoriosMouseClicked
        // TODO add your handling code here:
        new TelaRelatorios().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelRelatoriosMouseClicked

    private void labelLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLancamentosMouseClicked
        // TODO add your handling code here:
        new TelaLancamento().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelLancamentosMouseClicked

    private void labelSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSairMouseClicked
        // TODO add your handling code here:
        new TelaAbertura().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelSairMouseClicked

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
    private javax.swing.JButton buttonAddLancamento;
    private javax.swing.JButton buttonNovaDespesa;
    private javax.swing.JButton buttonNovaReceita;
    private javax.swing.JButton buttonPesquisa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField labelBarraPesquisa;
    private javax.swing.JLabel labelLancamentos;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelRelatorios;
    private javax.swing.JLabel labelSair;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelAddLancamento;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelNavBar;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
