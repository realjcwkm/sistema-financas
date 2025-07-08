package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.CentroCusto; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentroCustoDAO {

    public List<CentroCusto> listarTodosCentrosDeCusto() {
        List<CentroCusto> centrosDeCusto = new ArrayList<>();
        
        String sql = "SELECT id, titulo, descricao FROM tb_centro_custo ORDER BY titulo";

        try (Connection conn = ConexaoBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CentroCusto centroCusto = new CentroCusto();
                centroCusto.setId(rs.getInt("id"));
                centroCusto.setTitulo(rs.getString("titulo"));
                centroCusto.setDescricao(rs.getString("descricao"));
                centrosDeCusto.add(centroCusto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar centros de custo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar centros de custo do banco de dados.", e);
        }
        return centrosDeCusto;
    }
    
     public CentroCusto buscarCentroCustoPorId(int id) {
        String sql = "SELECT id, titulo, descricao FROM tb_centro_custo WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CentroCusto centroCusto = new CentroCusto();
                    centroCusto.setId(rs.getInt("id"));
                    centroCusto.setTitulo(rs.getString("titulo"));
                    centroCusto.setDescricao(rs.getString("descricao"));
                    return centroCusto;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar centro de custo por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar centro de custo por ID: " + e.getMessage(), e);
        }
        return null; 
    }
}