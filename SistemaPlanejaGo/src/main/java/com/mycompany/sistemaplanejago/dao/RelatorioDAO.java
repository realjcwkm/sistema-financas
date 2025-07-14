/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.dao.ConexaoBD;
import com.mycompany.sistemaplanejago.model.Lancamento;
import java.sql.*;
import java.util.*;

public class RelatorioDAO {

    
    public List<Object[]> buscarDespesasSemana() {
        List<Object[]> lista = new ArrayList<>();
        String sql = """
            SELECT DAYNAME(data_criacao) AS dia_semana, SUM(valor) AS total
            FROM tb_Lancamento l
            JOIN tb_Tipo_Lancamento t ON l.tipo = t.id
            WHERE t.titulo = 'Despesa' AND WEEK(data_criacao) = WEEK(CURDATE())
            GROUP BY dia_semana
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[] { rs.getString("dia_semana"), rs.getDouble("total") });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

   
    public List<Object[]> buscarTotalPorCategoria() {
        List<Object[]> lista = new ArrayList<>();
        String sql = """
            SELECT c.titulo AS categoria, SUM(l.valor) AS total
            FROM tb_Lancamento l
            JOIN tb_Categoria c ON l.categoria = c.id
            JOIN tb_Tipo_Lancamento t ON l.tipo = t.id
            WHERE t.titulo = 'Despesa'
            GROUP BY c.titulo
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[] { rs.getString("categoria"), rs.getDouble("total") });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    public Map<String, Double> buscarPercentualPagoNaoPago() {
        Map<String, Double> mapa = new HashMap<>();
        String sql = """
            SELECT status_pago, SUM(valor) AS total
            FROM tb_Lancamento
            WHERE tipo = 1
            GROUP BY status_pago
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            double pago = 0;
            double naoPago = 0;

            while (rs.next()) {
                boolean status = rs.getBoolean("status_pago");
                double total = rs.getDouble("total");
                if (status) pago += total;
                else naoPago += total;
            }
            double totalGeral = pago + naoPago;
            mapa.put("Pago", pago / totalGeral);
            mapa.put("Não Pago", naoPago / totalGeral);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapa;
    }

    
    public double calcularEconomiaPercentual() {
        String sql = """
            SELECT
                SUM(CASE WHEN WEEK(data_criacao) = WEEK(CURDATE()) THEN valor ELSE 0 END) AS semanaAtual,
                SUM(CASE WHEN WEEK(data_criacao) = WEEK(CURDATE()) - 1 THEN valor ELSE 0 END) AS semanaAnterior
            FROM tb_Lancamento
            WHERE tipo = 1
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                double atual = rs.getDouble("semanaAtual");
                double anterior = rs.getDouble("semanaAnterior");
                if (anterior > 0) {
                    return 100.0 * ((anterior - atual) / anterior);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    
    public List<Lancamento> listarLancamentosRecentes() {
        List<Lancamento> lista = new ArrayList<>();
        String sql = """
            SELECT l.id, l.descricao, l.valor, l.status_pago, l.data_criacao, c.titulo AS categoria
            FROM tb_Lancamento l
            JOIN tb_Categoria c ON l.categoria = c.id
            WHERE tipo = 1
            ORDER BY l.data_criacao DESC
            LIMIT 10
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lancamento l = new Lancamento();
                l.setId(rs.getInt("id"));
                l.setDescricao(rs.getString("descricao"));
                l.setValor(rs.getBigDecimal("valor"));
                l.setPago(rs.getBoolean("status_pago"));
                l.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                l.setCategoria(rs.getInt("categoria")); 
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public double getTotalSemanaPassada() {
    String sql = "SELECT SUM(valor) FROM tb_Lancamento WHERE tipo = 1 AND WEEK(data_criacao) = WEEK(CURDATE()) - 1";
    try (Connection conn = ConexaoBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0;
}

public double getTotalSemanaAtual() {
    String sql = "SELECT SUM(valor) FROM tb_Lancamento WHERE tipo = 1 AND WEEK(data_criacao) = WEEK(CURDATE())";
    try (Connection conn = ConexaoBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0;
}

}

