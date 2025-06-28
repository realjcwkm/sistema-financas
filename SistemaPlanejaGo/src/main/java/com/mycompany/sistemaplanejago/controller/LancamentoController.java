package com.mycompany.sistemaplanejago.controller;

import com.mycompany.sistemaplanejago.dao.LancamentoDAO;
import com.mycompany.sistemaplanejago.model.Lancamento;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            l.setTipo(1); 
            l.setCentroCusto(centroCusto);
            l.setLogDataInclusao(LocalDateTime.now());
            l.setUsuarioId(1); 

            
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
            l.setTipo(2); 

            //l.setStatusPago(true);     
            //l.setDataVencimento(null); 
            //l.setCentroCusto(0);      

            l.setLogDataInclusao(LocalDateTime.now());
            l.setUsuarioId(1); 

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
}