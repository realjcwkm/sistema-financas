package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Frequencia; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO {

    public List<Frequencia> listarTodasFrequencias() {
        List<Frequencia> frequencias = new ArrayList<>();
      
        String sql = "SELECT id, titulo, descricao FROM tb_frequencia"; 

        try (Connection conn = ConexaoBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Frequencia freq = new Frequencia();
                freq.setId(rs.getInt("id"));            
                freq.setTitulo(rs.getString("titulo"));  
                freq.setDescricao(rs.getString("descricao")); 
                frequencias.add(freq);                   
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar frequências: " + e.getMessage());
            e.printStackTrace(); 
        }
        return frequencias;
    }
    
    public Frequencia buscarFrequenciaPorId(int id) {
        String sql = "SELECT id, titulo, descricao FROM tb_frequencia WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Frequencia frequencia = new Frequencia();
                    frequencia.setId(rs.getInt("id"));
                    frequencia.setTitulo(rs.getString("titulo"));
                    frequencia.setDescricao(rs.getString("descricao"));
                    return frequencia;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar frequência por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar frequência por ID: " + e.getMessage(), e);
        }
        return null; 
    }
}