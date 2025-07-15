package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Categoria; // Importa o modelo Categoria
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listarCategorias(int tipo) { 
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, tipo FROM tb_categoria WHERE tipo = ? ORDER BY titulo";

        try (Connection conn = ConexaoBD.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setInt(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) { 
                while (rs.next()) {
                    Categoria cat = new Categoria(); 
                    cat.setId(rs.getInt("id"));
                    cat.setTitulo(rs.getString("titulo"));
                    cat.setDescricao(rs.getString("descricao"));
                    cat.setTipo(rs.getInt("tipo")); 
                    categorias.add(cat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao carregar categorias do banco de dados.", e);
        }
        return categorias;
    }
        
    public Categoria buscarCategoriaPorId(int id) {
        String sql = "SELECT id, titulo, descricao, tipo FROM tb_categoria WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("id"));
                    categoria.setTitulo(rs.getString("titulo"));
                    categoria.setDescricao(rs.getString("descricao"));
                    categoria.setTipo(rs.getInt("tipo"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar categoria por ID: " + e.getMessage(), e);
        }
        return null; 
    }
}
