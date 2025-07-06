package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Lancamento;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalDate; // Importe LocalDate
import java.math.BigDecimal; // Importe BigDecimal
import java.util.ArrayList;
import java.util.List;

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
                if (lancamento.getCentroCusto() != null) {
                    stmt.setInt(10, lancamento.getCentroCusto());
                } else {
                    stmt.setNull(10, Types.INTEGER);
                }
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
    
    public List<Lancamento> listarTodosLancamentos() {
        List<Lancamento> lancamentos = new ArrayList<>();
        String sql = "SELECT l.id, l.descricao, l.valor, l.status_pago, l.data_criacao, " +
                     "l.data_vencimento, l.frequencia, l.tipo, l.log_data_inclusao, l.log_data_alteracao, " +
                     "l.log_versao_registro, l.usuario_id, l.centro_custo, " +
                     "l.categoria, c.titulo AS categoria_titulo " + 
                     "FROM tb_Lancamento l " +
                     "INNER JOIN tb_categoria c ON l.categoria = c.id " +
                     "ORDER BY l.data_criacao ASC"; 

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lancamento lancamento = new Lancamento();
                lancamento.setId(rs.getInt("id"));
                lancamento.setDescricao(rs.getString("descricao"));
                lancamento.setValor(rs.getBigDecimal("valor"));
                lancamento.setStatusPago(rs.getBoolean("status_pago"));
                lancamento.setCategoria(rs.getInt("categoria")); // Pega o ID da categoria
                lancamento.setDataCriacao(rs.getDate("data_criacao") != null ? rs.getDate("data_criacao").toLocalDate() : null);
                lancamento.setDataVencimento(rs.getDate("data_vencimento") != null ? rs.getDate("data_vencimento").toLocalDate() : null);
                lancamento.setFrequencia(rs.getInt("frequencia"));
                lancamento.setTipo(rs.getInt("tipo")); 
                
                lancamento.setLogDataInclusao(rs.getTimestamp("log_data_inclusao") != null ? rs.getTimestamp("log_data_inclusao").toLocalDateTime() : null);
                lancamento.setLogDataAlteracao(rs.getTimestamp("log_data_alteracao") != null ? rs.getTimestamp("log_data_alteracao").toLocalDateTime() : null);
               
                Integer logVersaoRegistro = rs.getObject("log_versao_registro", Integer.class);
                lancamento.setLogVersaoRegistro(logVersaoRegistro != null ? logVersaoRegistro : 0); 
                
                Integer usuarioId = rs.getObject("usuario_id", Integer.class);
                lancamento.setUsuarioId(usuarioId != null ? usuarioId : 0); // Default 0 se nulo
                
                Integer centroCusto = rs.getObject("centro_custo", Integer.class);
                lancamento.setCentroCusto(centroCusto);

                lancamentos.add(lancamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar lançamentos do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return lancamentos;
    }
    
    public String getTituloCategoriaPorId(int categoriaId) {
        String titulo = null;
        String sql = "SELECT titulo FROM tb_categoria WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    titulo = rs.getString("titulo");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar título da categoria: " + e.getMessage());
            e.printStackTrace();
        }
        return titulo;
    }

    public boolean atualizarStatusLancamento(int idLancamento, boolean statusPago) {
        String sql = "UPDATE tb_Lancamento SET status_pago = ?, log_data_alteracao = ? WHERE id = ?";
        
        System.out.println("LancamentoDAO.atualizarStatusLancamento: Tentando atualizar ID " + idLancamento + " para status " + statusPago); // DEBUG
        System.out.println("LancamentoDAO.atualizarStatusLancamento: SQL: " + sql); 
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBoolean(1, statusPago);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now())); 
            stmt.setInt(3, idLancamento);
            
            System.out.println("LancamentoDAO.atualizarStatusLancamento: Executando update..."); 
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("LancamentoDAO.atualizarStatusLancamento: Sucesso! " + rowsAffected + " linha(s) afetada(s)."); 
                return true;
            } else {
                System.out.println("LancamentoDAO.atualizarStatusLancamento: Falha! Nenhuma linha afetada. ID " + idLancamento + " pode não existir."); 
                return false;
            }

        } catch (SQLException e) {
            System.err.println("LancamentoDAO.atualizarStatusLancamento: ERRO SQL ao atualizar status do lançamento: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        } catch (Exception e) {
            System.err.println("LancamentoDAO.atualizarStatusLancamento: ERRO INESPERADO ao atualizar status do lançamento: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }
}