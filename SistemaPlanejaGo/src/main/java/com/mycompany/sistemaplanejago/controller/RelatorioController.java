/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaplanejago.controller;

import com.mycompany.sistemaplanejago.dao.RelatorioDAO;
import com.mycompany.sistemaplanejago.model.Lancamento;

import java.util.List;
import java.util.Map;

public class RelatorioController {


    private RelatorioDAO relatorioDAO = new RelatorioDAO();

    public double getTotalSemanaAtual() {
        return relatorioDAO.getTotalSemanaAtual();
    }

    public double getTotalSemanaPassada() {
        return relatorioDAO.getTotalSemanaPassada();
    }
    public List<Object[]> buscarDespesasSemana() {
        return relatorioDAO.buscarDespesasSemana();
    }

    public List<Object[]> buscarTotalPorCategoria() {
        return relatorioDAO.buscarTotalPorCategoria();
    }

    public Map<String, Double> buscarPercentualPagoNaoPago() {
        return relatorioDAO.buscarPercentualPagoNaoPago();
    }

    public double calcularEconomiaPercentual() {
        return relatorioDAO.calcularEconomiaPercentual();
    }

    public List<Lancamento> listarLancamentosRecentes() {
        return relatorioDAO.listarLancamentosRecentes();
    }
}

