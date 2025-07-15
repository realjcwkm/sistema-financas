package com.mycompany.sistemaplanejago.view;

import com.mycompany.sistemaplanejago.componentes.graficoBarraHome.Bar;
import com.mycompany.sistemaplanejago.componentes.graficoBarraHome.BarChartPanel;
import com.mycompany.sistemaplanejago.componentes.graficoBarraHome.BarData;
import com.mycompany.sistemaplanejago.componentes.graficoLinhaHome.LineChartPanel;
import com.mycompany.sistemaplanejago.controller.LancamentoController;
import com.mycompany.sistemaplanejago.controller.UsuarioController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map.Entry;

public class TelaHome extends javax.swing.JFrame {

    private LancamentoController lancamentoController;
    private UsuarioController usuarioController;
    private LineChartPanel lineChartPanel;

    public TelaHome() {
        initComponents(); 
        lancamentoController = new LancamentoController();
        usuarioController = new UsuarioController();
        inicializarGraficoLinha();
        inicializarComponentesPersonalizados();
        
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
        

        

    }
    
    private void inicializarGraficoLinha() {
        lineChartPanel = new LineChartPanel(); 

        if (panelGraficoLinha != null) {
            panelGraficoLinha.setLayout(new BorderLayout()); 
            panelGraficoLinha.add(lineChartPanel, BorderLayout.CENTER);
            panelGraficoLinha.revalidate();
            panelGraficoLinha.repaint();
        } else {
            System.err.println("Erro: 'panelGraficoLinha' é NULL. Verifique se foi inicializado em initComponents().");
        }
    }
    
    private void inicializarComponentesPersonalizados() {
        carregarTotalDespesas();
        carregarTotalDespesasPagas();
        carregaRestoReceita();
        atualizarLabelCumprimento();
        atualizarGraficoDespesas(); 
        atualizarGraficoLinha();
    }
    
    private void atualizarLabelCumprimento() {
        if (labelCumprimento != null) {
            String nomeUsuario = usuarioController.getNomePrimeiroUsuario();
            labelCumprimento.setText("Olá " + nomeUsuario + "!");
        } else {
            System.err.println("Erro: 'labelCumprimento' é nulo ou não foi inicializado. Verifique o initComponents().");
        }
    }
    
    public void atualizarGraficoLinha() {
        if (lineChartPanel != null) {
            lineChartPanel.loadData(); 
        } else {
            System.err.println("Erro: Não foi possível atualizar o gráfico de linha, pois 'lineChartPanel' é NULL.");
        }
    }

    public void atualizarGraficoDespesas() {
        BarData data = new BarData();

        Color[] barColors = {
            new Color(180, 160, 205), 
            new Color(44, 41,102),  
            new Color(255, 160, 81)   
        };

        try {
            List<Entry<String, BigDecimal>> topGastos = lancamentoController.getTop3GastosPorCategoriaNoMes();

            for (int i = 0; i < topGastos.size(); i++) {
                Entry<String, BigDecimal> entry = topGastos.get(i);
                String categoriaNome = entry.getKey();
                BigDecimal valorGasto = entry.getValue();

                Color barColor = barColors[i % barColors.length];
                data.addBar(new Bar(valorGasto.doubleValue(), barColor, categoriaNome));
            }

            while (data.getBars().size() < 3) {
                int currentSize = data.getBars().size();
                Color defaultColor = barColors[currentSize % barColors.length];
                data.addBar(new Bar(0, defaultColor, "Sem Gasto Registrado"));
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar dados para o gráfico de barras: " + e.getMessage());
            e.printStackTrace();
            data.addBar(new Bar(0, Color.GRAY, "Erro"));
            data.addBar(new Bar(0, Color.GRAY, "Ao Carregar"));
            data.addBar(new Bar(0, Color.GRAY, "Dados"));
        }

        BarChartPanel barChartPanel = new BarChartPanel(data);

        if (panelGraficoBarra != null) {
            panelGraficoBarra.setLayout(new java.awt.BorderLayout());
            panelGraficoBarra.removeAll(); // Remove o gráfico antigo
            panelGraficoBarra.add(barChartPanel, java.awt.BorderLayout.CENTER); // Adiciona o novo gráfico
            panelGraficoBarra.revalidate(); // Revalida o layout
            panelGraficoBarra.repaint();   // Redesenha
        } else {
            System.err.println("Erro: panelGraficoBarra é nulo ou não foi inicializado.");
        }
    }        

    public void carregarTotalDespesas() {
        BigDecimal totalDespesas = lancamentoController.calcularTotalDespesas();
        if (labelValorTotalPagar != null) { 
            labelValorTotalPagar.setText(totalDespesas.toPlainString());
        } 
    }
    
    public void carregarTotalDespesasPagas() {
        BigDecimal totalDespesasPagas = lancamentoController.calcularTotalDespesasPagas();
        if (labelValorTotalPago != null) { 
            labelValorTotalPago.setText(totalDespesasPagas.toPlainString());
        }
    }
    
    public void carregaRestoReceita(){
        BigDecimal totalReceita = lancamentoController.calculaRestanteReceita();
        if (labelValorReceita != null) { 
            labelValorReceita.setText(totalReceita.toPlainString());
            if (totalReceita.compareTo(BigDecimal.ZERO) < 0) {
                labelValorReceita.setForeground(new Color(255, 103, 103));
            } else {
                labelValorReceita.setForeground(new Color(0, 126, 244));
            }
        }
    }
    /////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
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
        panelGraficoLinha = new javax.swing.JPanel();
        panelRegiaoBarra = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelGraficoBarra = new javax.swing.JPanel();
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
        labelNovaDespesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNovaDespesaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNovaDespesaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNovaDespesaMouseExited(evt);
            }
        });

        labelNovaReceita.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelNovaReceita.setForeground(new java.awt.Color(44, 41, 102));
        labelNovaReceita.setText("+ Criar Nova Receita");
        labelNovaReceita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNovaReceitaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNovaReceitaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNovaReceitaMouseExited(evt);
            }
        });

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
                .addGap(210, 210, 210)
                .addComponent(panelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout panelGraficoLinhaLayout = new javax.swing.GroupLayout(panelGraficoLinha);
        panelGraficoLinha.setLayout(panelGraficoLinhaLayout);
        panelGraficoLinhaLayout.setHorizontalGroup(
            panelGraficoLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficoLinhaLayout.setVerticalGroup(
            panelGraficoLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(panelConfiguracaoLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
            .addComponent(panelGraficoLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConfiguracaoLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficoLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout panelGraficoBarraLayout = new javax.swing.GroupLayout(panelGraficoBarra);
        panelGraficoBarra.setLayout(panelGraficoBarraLayout);
        panelGraficoBarraLayout.setHorizontalGroup(
            panelGraficoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficoBarraLayout.setVerticalGroup(
            panelGraficoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

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
            .addComponent(panelGraficoBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRegiaoBarraLayout.setVerticalGroup(
            panelRegiaoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegiaoBarraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficoBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void labelNovaDespesaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaDespesaMouseEntered
        // TODO add your handling code here:
        labelNovaDespesa.setForeground(new Color(255, 160, 81));
    }//GEN-LAST:event_labelNovaDespesaMouseEntered

    private void labelNovaReceitaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaReceitaMouseEntered
        // TODO add your handling code here:
        labelNovaReceita.setForeground(new Color(255, 160, 81));
    }//GEN-LAST:event_labelNovaReceitaMouseEntered

    private void labelNovaDespesaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaDespesaMouseExited
        // TODO add your handling code here:
        labelNovaDespesa.setForeground(new Color(44, 41, 102));
    }//GEN-LAST:event_labelNovaDespesaMouseExited

    private void labelNovaReceitaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaReceitaMouseExited
        // TODO add your handling code here:
        labelNovaReceita.setForeground(new Color(44, 41, 102));
    }//GEN-LAST:event_labelNovaReceitaMouseExited

    private void labelNovaDespesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaDespesaMouseClicked
        // TODO add your handling code here:
        DespesaForm Despesaformulario = new DespesaForm(this, true);
        Despesaformulario.setVisible(true);
        carregarTotalDespesas();
        carregarTotalDespesasPagas();
        carregaRestoReceita();
        atualizarGraficoDespesas();
        atualizarGraficoLinha();
    }//GEN-LAST:event_labelNovaDespesaMouseClicked

    private void labelNovaReceitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaReceitaMouseClicked
        // TODO add your handling code here:
        ReceitaForm Receitaformulario = new ReceitaForm(this, true);
        Receitaformulario.setVisible(true);
        carregarTotalDespesas();
        carregarTotalDespesasPagas();
        carregaRestoReceita();
        atualizarGraficoDespesas();
        atualizarGraficoLinha();
    }//GEN-LAST:event_labelNovaReceitaMouseClicked

    private void labelPlanejaGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlanejaGoMouseClicked
        // TODO add your handling code here:
        new TelaHome().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelPlanejaGoMouseClicked

    private void labelLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLancamentosMouseClicked
        // TODO add your handling code here:
        new TelaLancamento().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelLancamentosMouseClicked

    private void labelRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRelatoriosMouseClicked
        // TODO add your handling code here:
        new TelaRelatorios().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labelRelatoriosMouseClicked

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelAcessoRapido;
    private javax.swing.JLabel labelCumprimento;
    private javax.swing.JLabel labelFraseDia;
    private javax.swing.JLabel labelLancamentos;
    private javax.swing.JLabel labelNovaDespesa;
    private javax.swing.JLabel labelNovaReceita;
    private javax.swing.JLabel labelPlanejaGo;
    private javax.swing.JLabel labelRelatorios;
    private javax.swing.JLabel labelSair;
    private javax.swing.JLabel labelTotalPagar;
    private javax.swing.JLabel labelTotalPago;
    private javax.swing.JLabel labelTotalReceita;
    private javax.swing.JLabel labelValorReceita;
    private javax.swing.JLabel labelValorTotalPagar;
    private javax.swing.JLabel labelValorTotalPago;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelCentralizador;
    private javax.swing.JPanel panelConfiguracaoLinhas;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelGraficoBarra;
    private javax.swing.JPanel panelGraficoLinha;
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
