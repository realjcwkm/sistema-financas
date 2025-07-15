package com.mycompany.sistemaplanejago.controller;

import com.mycompany.sistemaplanejago.dao.LancamentoDAO;
import com.mycompany.sistemaplanejago.model.Lancamento;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LancamentoController {

    private final LancamentoDAO lancamentoDao = new LancamentoDAO();

    public boolean cadastrarDespesa(String descricao, String valorStr, String statusPagoStr,
                                    String dataCriacaoStr, String dataVencimentoStr,
                                    String frequenciaStr, String centroCustoStr, String categoriaStr) {
        
        if (descricao.isEmpty() || valorStr.isEmpty() || statusPagoStr.isEmpty()
                || dataCriacaoStr.isEmpty() || dataVencimentoStr.isEmpty()
                || frequenciaStr.isEmpty() || centroCustoStr.isEmpty() || categoriaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos para Despesa são obrigatórios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));
            boolean status = statusPagoStr.equals("true");
            int frequencia = Integer.parseInt(frequenciaStr);
            int centroCusto = Integer.parseInt(centroCustoStr);
            int categoria = Integer.parseInt(categoriaStr);

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtCriacao = LocalDate.parse(dataCriacaoStr, fmt);
            LocalDate dtVencimento = LocalDate.parse(dataVencimentoStr, fmt);

            Lancamento l = new Lancamento();
            l.setDescricao(descricao);
            l.setValor(valor);
            l.setStatusPago(status);
            l.setCategoria(categoria);
            l.setDataCriacao(dtCriacao);
            l.setDataVencimento(dtVencimento);
            l.setFrequencia(frequencia);
            l.setTipo(1); // Despesa
            l.setCentroCusto(centroCusto);
            l.setLogDataInclusao(LocalDateTime.now());
            l.setUsuarioId(1); // Exemplo de ID de usuário

            return lancamentoDao.criarLancamento(l, 1);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro de formato numérico ou booleano: " + e.getMessage(), "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Erro de formato de data. Use o formato DD/MM/AAAA: " + e.getMessage(), "Erro de Data", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado ao cadastrar despesa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean cadastrarReceita(String descricao, String valorStr,
                                    String dataCriacaoStr, String frequenciaStr, String categoriaStr) {
        
        if (descricao.isEmpty() || valorStr.isEmpty()
                || dataCriacaoStr.isEmpty() || frequenciaStr.isEmpty() || categoriaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios para Receita são obrigatórios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            
            BigDecimal valor = new BigDecimal(valorStr.replace(",", "."));
            int frequencia = Integer.parseInt(frequenciaStr);
            int categoria = Integer.parseInt(categoriaStr);

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtCriacao = LocalDate.parse(dataCriacaoStr, fmt);

            Lancamento l = new Lancamento();
            l.setDescricao(descricao);
            l.setValor(valor);
            l.setCategoria(categoria);
            l.setDataCriacao(dtCriacao);
            l.setFrequencia(frequencia);
            l.setTipo(2); // Receita

            l.setLogDataInclusao(LocalDateTime.now());
            l.setUsuarioId(1); // Exemplo de ID de usuário

            return lancamentoDao.criarLancamento(l, 2);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter valor, frequência ou categoria. Verifique se são números válidos: " + e.getMessage(), "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter a data de criação. Use o formato DD/MM/AAAA: " + e.getMessage(), "Erro de Data", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado ao cadastrar receita: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public List<Lancamento> listarLancamentos() {
        return lancamentoDao.listarTodosLancamentos();
    }
    
    public String getNomeCategoria(int categoriaId) {
        String nome = lancamentoDao.getTituloCategoriaPorId(categoriaId);
        return nome != null ? nome : "Desconhecida";
    }
    
    public String getNomeTipoLancamento(int tipoId) {
        switch (tipoId) {
            case 1:
                return "Despesa";
            case 2:
                return "Receita";
            default:
                return "Desconhecido"; // Para IDs de tipo não reconhecidos
        }
    }
    
    public boolean atualizarStatusLancamento(int idLancamento, boolean statusPago) {
        return lancamentoDao.atualizarStatusLancamento(idLancamento, statusPago);
    }
    
    public boolean excluirLancamento(int idLancamento) {
        if (idLancamento <= 0) {
            System.err.println("Controller: ID de lançamento inválido para exclusão (ID <= 0).");
            return false;
        }

        return lancamentoDao.excluirLancamento(idLancamento);
    }
    
    public boolean atualizarDespesa(int id, String descricao, BigDecimal valor, boolean statusPago,
                                     LocalDate dataCriacao, LocalDate dataVencimento,
                                     int frequenciaId, int categoriaId, Integer centroCustoId) {
   
        if (id <= 0 || descricao == null || descricao.trim().isEmpty() || valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 ||
            dataCriacao == null || frequenciaId <= 0 || categoriaId <= 0) {
            System.err.println("LancamentoController.atualizarDespesa: Dados básicos de despesa inválidos para ID: " + id);
            return false;
        }

        Lancamento lancamentoExistente = lancamentoDao.buscarLancamentoPorId(id);

        // Verifca se o lançamento foi encontrado e se é realmente uma despesa 
        if (lancamentoExistente == null || lancamentoExistente.getTipo() != 1) {
            System.err.println("LancamentoController.atualizarDespesa: Despesa não encontrada ou tipo incorreto para o ID: " + id);
            return false;
        }

        lancamentoExistente.setDescricao(descricao);
        lancamentoExistente.setValor(valor);
        lancamentoExistente.setStatusPago(statusPago);
        lancamentoExistente.setCategoria(categoriaId);
        lancamentoExistente.setDataCriacao(dataCriacao);
        lancamentoExistente.setDataVencimento(dataVencimento);
        lancamentoExistente.setFrequencia(frequenciaId);
        lancamentoExistente.setCentroCusto(centroCustoId);
        lancamentoExistente.setTipo(1); // Mantém o tipo como Despesa

        return lancamentoDao.atualizarLancamento(lancamentoExistente);
    }
    
    public boolean atualizarReceita(int id, String descricao, BigDecimal valor,
                                    LocalDate dataCriacao, int frequenciaId, int categoriaId) {
        
        if (id <= 0 || descricao == null || descricao.trim().isEmpty() || valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 ||
            dataCriacao == null || frequenciaId <= 0 || categoriaId <= 0) {
            System.err.println("LancamentoController.atualizarReceita: Dados básicos de receita inválidos para ID: " + id);
            return false;
        }

        Lancamento lancamentoExistente = lancamentoDao.buscarLancamentoPorId(id);
        
        // Verifica se é realmente uma receita 
        if (lancamentoExistente == null || lancamentoExistente.getTipo() != 2) {
            System.err.println("LancamentoController.atualizarReceita: Receita não encontrada ou tipo incorreto para o ID: " + id);
            return false;
        }

        lancamentoExistente.setDescricao(descricao);
        lancamentoExistente.setValor(valor);
        lancamentoExistente.setDataCriacao(dataCriacao);
        lancamentoExistente.setFrequencia(frequenciaId);
        lancamentoExistente.setCategoria(categoriaId);
        lancamentoExistente.setTipo(2); 

        lancamentoExistente.setStatusPago(false); 
        lancamentoExistente.setDataVencimento(null);
        lancamentoExistente.setCentroCusto(null);

        return lancamentoDao.atualizarLancamento(lancamentoExistente);
    }
     
     public BigDecimal calcularTotalDespesas() {
        return lancamentoDao.getSomaTotalDespesasNaoPagas();
    }

    public BigDecimal calcularTotalDespesasPagas() {
        return lancamentoDao.getSomaTotalDespesasPagas(); 
    }

    public BigDecimal calculaRestanteReceita() {
        BigDecimal totalReceitas = lancamentoDao.getSomaTotalReceitas();
        BigDecimal totalDespesasPagas = lancamentoDao.getSomaTotalDespesasPagas();

        return totalReceitas.subtract(totalDespesasPagas);
    }
    
    public List<Map.Entry<String, BigDecimal>> getTop3GastosPorCategoriaNoMes() {
        List<Map.Entry<String, BigDecimal>> topCategoriasComNomes = new ArrayList<>();
        try {
            Map<Integer, BigDecimal> topCategoriasIds = lancamentoDao.getTop3CategoriasDespesasMesAtual();

            for (Map.Entry<Integer, BigDecimal> entry : topCategoriasIds.entrySet()) {
                int categoriaId = entry.getKey();
                BigDecimal totalGasto = entry.getValue();
                String nomeCategoria = lancamentoDao.getTituloCategoriaPorId(categoriaId); // Chama o método existente

                if (nomeCategoria == null || nomeCategoria.trim().isEmpty()) {
                    nomeCategoria = "Categoria Desconhecida (ID: " + categoriaId + ")";
                }
                topCategoriasComNomes.add(new SimpleEntry<>(nomeCategoria, totalGasto));
            }
        } catch (RuntimeException e) {
            System.err.println("Erro no controller ao buscar top 3 gastos por categoria: " + e.getMessage());
            e.printStackTrace(); 
            return new ArrayList<>(); // Retorna lista vazia em caso de erro
        }
        return topCategoriasComNomes;
    }
    
    public List<Map.Entry<String, BigDecimal>> getReceitasParaGraficoPorMesSemestreAtualComNome() {
        List<Map.Entry<String, BigDecimal>> listaComNomes = new ArrayList<>();
        try {
            Map<Integer, BigDecimal> receitasPorMes = lancamentoDao.getReceitasParaGraficoPorMesSemestreAtual();

            // Itera pelos meses de Julho (7) a Dezembro (12)
            for (int mesNum = 7; mesNum <= 12; mesNum++) {
                Month mesEnum = Month.of(mesNum); 
                String nomeMes = mesEnum.getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")); 

                BigDecimal total = receitasPorMes.getOrDefault(mesNum, BigDecimal.ZERO);
                listaComNomes.add(new SimpleEntry<>(nomeMes, total));
            }
        } catch (RuntimeException e) {
            System.err.println("Erro no controller ao obter receitas para o gráfico de linha: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
        return listaComNomes;
    }

    public List<Map.Entry<String, BigDecimal>> getDespesasParaGraficoPorMesSemestreAtualComNome() {
        List<Map.Entry<String, BigDecimal>> listaComNomes = new ArrayList<>();
        try {
            Map<Integer, BigDecimal> despesasPorMes = lancamentoDao.getDespesasParaGraficoPorMesSemestreAtual();

            // Itera pelos meses de Julho (7) a Dezembro (12)
            for (int mesNum = 7; mesNum <= 12; mesNum++) {
                Month mesEnum = Month.of(mesNum);
                String nomeMes = mesEnum.getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"));

                BigDecimal total = despesasPorMes.getOrDefault(mesNum, BigDecimal.ZERO);
                listaComNomes.add(new SimpleEntry<>(nomeMes, total));
            }
        } catch (RuntimeException e) {
            System.err.println("Erro no controller ao obter despesas para o gráfico de linha: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
        return listaComNomes;
    }
    
    public List<Lancamento> pesquisarLancamentos(String termoPesquisa) {
    if (termoPesquisa == null || termoPesquisa.trim().isEmpty()) {
        return lancamentoDao.listarTodosLancamentos();
    }
    return lancamentoDao.pesquisarLancamentos(termoPesquisa);
}
}