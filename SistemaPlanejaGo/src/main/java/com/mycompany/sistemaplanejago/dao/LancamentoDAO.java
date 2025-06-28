package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Lancamento;
import java.sql.*;
import java.time.LocalDateTime;

public class LancamentoDAO {

    public boolean criarLancamento(Lancamento lancamento, int tipoL) {
        String sql;

        if (tipoL == 1) { // Despesa
            sql = "INSERT INTO tb_Lancamento (descricao, valor, status_pago, categoria, data_criacao, data_vencimento, frequencia, tipo, log_data_inclusao, centro_custo, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (tipoL == 2) { // Receita
            sql = "INSERT INTO tb_Lancamento (descricao, valor, categoria, data_criacao, frequencia, tipo, log_data_inclusao, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            System.err.println("Tipo de lançamento inválido: " + tipoL);
            return false; 
        }

        try (Connection conn = ConexaoBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (tipoL == 1) { 
                stmt.setString(1, lancamento.getDescricao());
                stmt.setBigDecimal(2, lancamento.getValor());
                stmt.setBoolean(3, lancamento.isStatusPago());
                stmt.setInt(4, lancamento.getCategoria());
                stmt.setDate(5, Date.valueOf(lancamento.getDataCriacao()));
                
                if (lancamento.getDataVencimento() != null) {
                    stmt.setDate(6, Date.valueOf(lancamento.getDataVencimento()));
                } else {
                    stmt.setNull(6, Types.DATE); 
                }
                
                stmt.setInt(7, lancamento.getFrequencia());
                stmt.setInt(8, tipoL); 
                stmt.setTimestamp(9, Timestamp.valueOf(lancamento.getLogDataInclusao()));
                stmt.setInt(10, lancamento.getCentroCusto());
                stmt.setInt(11, lancamento.getUsuarioId());

            } else if (tipoL == 2) { 
                stmt.setString(1, lancamento.getDescricao());
                stmt.setBigDecimal(2, lancamento.getValor());
                stmt.setInt(3, lancamento.getCategoria());
                stmt.setDate(4, Date.valueOf(lancamento.getDataCriacao()));
                stmt.setInt(5, lancamento.getFrequencia());
                stmt.setInt(6, tipoL); 
                stmt.setTimestamp(7, Timestamp.valueOf(lancamento.getLogDataInclusao()));
                stmt.setInt(8, lancamento.getUsuarioId());
            }

            stmt.executeUpdate(); 
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao criar lançamento no banco de dados: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }
    

}