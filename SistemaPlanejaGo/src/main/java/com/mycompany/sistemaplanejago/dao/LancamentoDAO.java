package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Lancamento;
import java.sql.*;
import java.time.LocalDateTime;

public class LancamentoDAO {
    public boolean criarLancamento(Lancamento lancamento, int tipoL) {
        String sql;
        if (tipoL == 1) {
            sql = "INSERT INTO tb_Lancamento (descricao, valor, status_pago, categoria, data_criacao, data_vencimento, frequencia, tipo, log_data_inclusao, centro_custo, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (tipoL == 2) {
            sql = "INSERT INTO tb_Lancamento (descricao, valor, categoria, data_criacao, frequencia, tipo, log_data_inclusao, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            return false;
        }

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int i = 1;
            stmt.setString(i++, lancamento.getDescricao());
            stmt.setBigDecimal(i++, lancamento.getValor());
            stmt.setBoolean(i++, lancamento.isStatusPago());
            stmt.setInt(i++, lancamento.getCategoria());
            stmt.setDate(i++, Date.valueOf(lancamento.getDataCriacao()));

            if (tipoL == 1) {
                stmt.setDate(i++, Date.valueOf(lancamento.getDataVencimento()));
                stmt.setInt(i++, lancamento.getFrequencia());
                stmt.setInt(i++, tipoL);
                stmt.setTimestamp(i++, Timestamp.valueOf(lancamento.getLogDataInclusao()));
                stmt.setInt(i++, lancamento.getCentroCusto());
                stmt.setInt(i++, lancamento.getUsuarioId());
            } else {
                stmt.setInt(i++, lancamento.getFrequencia());
                stmt.setInt(i++, tipoL);
                stmt.setTimestamp(i++, Timestamp.valueOf(lancamento.getLogDataInclusao()));
                stmt.setInt(i++, lancamento.getUsuarioId());
            }

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao criar lançamento no banco de dados: " + e.getMessage());
            return false;
        }
    }
}
