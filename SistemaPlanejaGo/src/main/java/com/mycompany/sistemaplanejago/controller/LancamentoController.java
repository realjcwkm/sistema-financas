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
        // validação
        if (descricao.isEmpty() || valorStr.isEmpty() || statusPagoStr.isEmpty()
                || dataCriacaoStr.isEmpty() || dataVencimentoStr.isEmpty()
                || frequenciaStr.isEmpty() || centroCustoStr.isEmpty() || categoriaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.WARNING_MESSAGE);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean cadastrarReceita(String descricao, String valorStr, String dataCriacaoStr, String frequenciaStr) {

        if (descricao == null || descricao.trim().isEmpty() ||
            valorStr == null || valorStr.trim().isEmpty() ||
            dataCriacaoStr == null || dataCriacaoStr.trim().isEmpty() ||
            frequenciaStr == null || frequenciaStr.trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        BigDecimal valor;
        int frequencia;
        LocalDate dataCriacao;

        try {
            valor = new BigDecimal(valorStr.replace(",", "."));
            frequencia = Integer.parseInt(frequenciaStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataCriacao = LocalDate.parse(dataCriacaoStr, formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter campos: " + e.getMessage(), "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(descricao);
        lancamento.setValor(valor);
        lancamento.setDataCriacao(dataCriacao);
        lancamento.setFrequencia(frequencia);
        lancamento.setTipo(2); 
        lancamento.setLogDataInclusao(LocalDateTime.now());
        lancamento.setUsuarioId(1); 

        return lancamentoDao.criarLancamento(lancamento, 2);
    }
}
