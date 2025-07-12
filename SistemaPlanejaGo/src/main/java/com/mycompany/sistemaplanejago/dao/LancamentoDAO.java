package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Lancamento;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDAO {

    public boolean criarLancamento(Lancamento lancamento, int tipoL) {
        String sql;

        if (tipoL == 1) {
            sql = "INSERT INTO tb_Lancamento (descricao, valor, status_pago, categoria, data_criacao, data_vencimento, frequencia, tipo, log_data_inclusao, centro_custo, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (tipoL == 2) {
            sql = "INSERT INTO tb_Lancamento (descricao, valor, categoria, data_criacao, frequencia, tipo, log_data_inclusao, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            System.err.println("LancamentoDAO.criarLancamento: Tipo de lançamento inválido: " + tipoL);
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
            System.err.println("LancamentoDAO.criarLancamento: Erro ao criar lançamento no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Lancamento> listarTodosLancamentos() {
        List<Lancamento> lancamentos = new ArrayList<>();
        String sql = "SELECT id, descricao, valor, status_pago, categoria, data_criacao, data_vencimento, " +
                     "frequencia, tipo, log_data_inclusao, log_data_alteracao, log_versao_registro, " +
                     "usuario_id, centro_custo FROM tb_Lancamento ORDER BY data_criacao ASC";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lancamento lancamento = new Lancamento();
                lancamento.setId(rs.getInt("id"));
                lancamento.setDescricao(rs.getString("descricao"));
                lancamento.setValor(rs.getBigDecimal("valor"));
                
                Object statusPagoObj = rs.getObject("status_pago");
                lancamento.setStatusPago(statusPagoObj != null ? (boolean) statusPagoObj : false);

                lancamento.setCategoria(rs.getInt("categoria"));
                lancamento.setDataCriacao(rs.getDate("data_criacao") != null ? rs.getDate("data_criacao").toLocalDate() : null);
                lancamento.setDataVencimento(rs.getDate("data_vencimento") != null ? rs.getDate("data_vencimento").toLocalDate() : null);
                lancamento.setFrequencia(rs.getInt("frequencia"));
                lancamento.setTipo(rs.getInt("tipo"));
                
                lancamento.setLogDataInclusao(rs.getTimestamp("log_data_inclusao") != null ? rs.getTimestamp("log_data_inclusao").toLocalDateTime() : null);
                lancamento.setLogDataAlteracao(rs.getTimestamp("log_data_alteracao") != null ? rs.getTimestamp("log_data_alteracao").toLocalDateTime() : null);
                lancamento.setLogVersaoRegistro(rs.getObject("log_versao_registro", Integer.class) != null ? rs.getObject("log_versao_registro", Integer.class) : 0);
                
                Integer usuarioId = rs.getObject("usuario_id", Integer.class);
                lancamento.setUsuarioId(usuarioId != null ? usuarioId : 0);
                
                Object centroCustoObj = rs.getObject("centro_custo");
                lancamento.setCentroCusto(centroCustoObj != null ? (Integer) centroCustoObj : null);

                lancamentos.add(lancamento);
            }
        } catch (SQLException e) {
            System.err.println("LancamentoDAO.listarTodosLancamentos: Erro ao listar lançamentos do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return lancamentos;
    }

    public Lancamento buscarLancamentoPorId(int id) {
        String sql = "SELECT id, descricao, valor, status_pago, categoria, data_criacao, data_vencimento, " +
                     "frequencia, tipo, log_data_inclusao, log_data_alteracao, log_versao_registro, " +
                     "usuario_id, centro_custo FROM tb_Lancamento WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Lancamento lancamento = new Lancamento();
                    lancamento.setId(rs.getInt("id"));
                    lancamento.setDescricao(rs.getString("descricao"));
                    lancamento.setValor(rs.getBigDecimal("valor"));
                    
                    Object statusPagoObj = rs.getObject("status_pago");
                    lancamento.setStatusPago(statusPagoObj != null ? (boolean) statusPagoObj : false);

                    lancamento.setCategoria(rs.getInt("categoria"));
                    lancamento.setDataCriacao(rs.getDate("data_criacao") != null ? rs.getDate("data_criacao").toLocalDate() : null);
                    lancamento.setDataVencimento(rs.getDate("data_vencimento") != null ? rs.getDate("data_vencimento").toLocalDate() : null);
                    lancamento.setFrequencia(rs.getInt("frequencia"));
                    lancamento.setTipo(rs.getInt("tipo"));
                    lancamento.setLogDataInclusao(rs.getTimestamp("log_data_inclusao") != null ? rs.getTimestamp("log_data_inclusao").toLocalDateTime() : null);
                    lancamento.setLogDataAlteracao(rs.getTimestamp("log_data_alteracao") != null ? rs.getTimestamp("log_data_alteracao").toLocalDateTime() : null);
                    lancamento.setLogVersaoRegistro(rs.getObject("log_versao_registro", Integer.class) != null ? rs.getObject("log_versao_registro", Integer.class) : 0);
                    lancamento.setUsuarioId(rs.getObject("usuario_id", Integer.class) != null ? rs.getObject("usuario_id", Integer.class) : 0);
                    Object centroCustoObj = rs.getObject("centro_custo");
                    lancamento.setCentroCusto(centroCustoObj != null ? (Integer) centroCustoObj : null);
                    
                    return lancamento;
                }
            }
        } catch (SQLException e) {
            System.err.println("LancamentoDAO.buscarLancamentoPorId: Erro ao buscar lançamento por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar lançamento por ID", e);
        }
        return null;
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
            System.err.println("LancamentoDAO.getTituloCategoriaPorId: Erro ao buscar título da categoria: " + e.getMessage());
            e.printStackTrace();
        }
        return titulo;
    }

    public boolean atualizarStatusLancamento(int idLancamento, boolean statusPago) {
        String sql = "UPDATE tb_Lancamento SET status_pago = ?, log_data_alteracao = ?, log_versao_registro = log_versao_registro + 1 WHERE id = ?";

        System.out.println("LancamentoDAO.atualizarStatusLancamento: Tentando atualizar ID " + idLancamento + " para status " + statusPago);

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, statusPago);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, idLancamento);

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

    public boolean excluirLancamento(int idLancamento) {
        String sql = "DELETE FROM tb_Lancamento WHERE id = ?";

        System.out.println("LancamentoDAO.excluirLancamento: Tentando excluir lançamento com ID: " + idLancamento);

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLancamento);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("LancamentoDAO.excluirLancamento: Sucesso! " + rowsAffected + " linha(s) afetada(s).");
                return true;
            } else {
                System.out.println("LancamentoDAO.excluirLancamento: Falha! Nenhuma linha afetada. Lançamento ID " + idLancamento + " pode não existir.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("LancamentoDAO.excluirLancamento: ERRO SQL ao excluir lançamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("LancamentoDAO.excluirLancamento: ERRO INESPERADO ao excluir lançamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarLancamento(Lancamento lancamento) {
        String sql = "UPDATE tb_Lancamento SET "
                   + "descricao = ?, valor = ?, status_pago = ?, categoria = ?, "
                   + "data_criacao = ?, data_vencimento = ?, frequencia = ?, "
                   + "centro_custo = ?, log_data_alteracao = ?, log_versao_registro = ? "
                   + "WHERE id = ?";

        System.out.println("LancamentoDAO.atualizarLancamento: Tentando atualizar ID " + lancamento.getId() + " do tipo " + lancamento.getTipo());

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lancamento.getDescricao());
            stmt.setBigDecimal(2, lancamento.getValor());

            if (lancamento.getTipo() == 1) { // 1 é Despesa
                stmt.setBoolean(3, lancamento.isStatusPago());
            } else { 
                stmt.setNull(3, Types.BOOLEAN);
            }

            stmt.setInt(4, lancamento.getCategoria());
            stmt.setDate(5, Date.valueOf(lancamento.getDataCriacao()));

            if (lancamento.getDataVencimento() != null) {
                stmt.setDate(6, Date.valueOf(lancamento.getDataVencimento()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.setInt(7, lancamento.getFrequencia());

            if (lancamento.getCentroCusto() != null) {
                stmt.setInt(8, lancamento.getCentroCusto());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(10, lancamento.getLogVersaoRegistro() + 1);
            stmt.setInt(11, lancamento.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("LancamentoDAO.atualizarLancamento: Sucesso! " + rowsAffected + " linha(s) afetada(s).");
                return true;
            } else {
                System.out.println("LancamentoDAO.atualizarLancamento: Falha! Nenhuma linha afetada para o ID " + lancamento.getId());
                return false;
            }

        } catch (SQLException e) {
            System.err.println("LancamentoDAO.atualizarLancamento: ERRO SQL ao atualizar lançamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("LancamentoDAO.atualizarLancamento: ERRO INESPERADO ao atualizar lançamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public BigDecimal getSomaTotalDespesasNaoPagas() {
        BigDecimal totalDespesas = BigDecimal.ZERO;

        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDiaMes = hoje.with(TemporalAdjusters.firstDayOfMonth());
        var primeiroDiaProximoMes = hoje.with(TemporalAdjusters.firstDayOfNextMonth());

        String sql = "SELECT SUM(valor) AS total FROM tb_Lancamento WHERE tipo = 1 AND status_pago = false " +
                     "AND data_criacao >= ? AND data_criacao < ?"; // datas como parâmetros

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da consulta como java.sql.Date
            stmt.setDate(1, Date.valueOf(primeiroDiaMes));
            stmt.setDate(2, Date.valueOf(primeiroDiaProximoMes));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalDespesas = rs.getBigDecimal("total");
                    if (totalDespesas == null) {
                        totalDespesas = BigDecimal.ZERO;
                    }
                }
            }
        } catch (SQLException e) {
            return BigDecimal.ZERO;
        }
        return totalDespesas;
    }

    public BigDecimal getSomaTotalDespesasPagas() {
        BigDecimal totalDespesas = BigDecimal.ZERO;

        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDiaMes = hoje.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate primeiroDiaProximoMes = hoje.with(TemporalAdjusters.firstDayOfNextMonth());

        String sql = "SELECT SUM(valor) AS total FROM tb_Lancamento WHERE tipo = 1 AND status_pago = true " +
                     "AND data_criacao >= ? AND data_criacao < ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(primeiroDiaMes));
            stmt.setDate(2, Date.valueOf(primeiroDiaProximoMes));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalDespesas = rs.getBigDecimal("total");
                    if (totalDespesas == null) {
                        totalDespesas = BigDecimal.ZERO;
                    }
                }
            }
        } catch (SQLException e) {
            return BigDecimal.ZERO;
        }
        return totalDespesas;
    }

    public BigDecimal getSomaTotalReceitas() {
        BigDecimal totalReceitas = BigDecimal.ZERO;

        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDiaMes = hoje.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate primeiroDiaProximoMes = hoje.with(TemporalAdjusters.firstDayOfNextMonth());

        String sql = "SELECT SUM(valor) AS total FROM tb_Lancamento WHERE tipo = 2 " +
                     "AND data_criacao >= ? AND data_criacao < ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(primeiroDiaMes));
            stmt.setDate(2, Date.valueOf(primeiroDiaProximoMes));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalReceitas = rs.getBigDecimal("total");
                    if (totalReceitas == null) {
                        totalReceitas = BigDecimal.ZERO;
                    }
                }
            }
        } catch (SQLException e) {
            return BigDecimal.ZERO;
        }
        return totalReceitas;
    }
}