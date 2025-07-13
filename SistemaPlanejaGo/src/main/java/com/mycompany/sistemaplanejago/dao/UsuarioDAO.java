package com.mycompany.sistemaplanejago.dao;

import com.mycompany.sistemaplanejago.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UsuarioDAO {
    public boolean criarUsuario(Usuario usuario) {
        
        String sql = "INSERT INTO tb_Usuario (nome, email, data_nascimento, senha, log_data_inclusao) VALUES (?, ?, ?, ?, NOW())";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoBD.getConnection(); 
            if (conn == null) {
                return false;
            }

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getSenha());

            stmt.executeUpdate(); 

            
        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário no banco de dados: " + e.getMessage());
            return false;
        } finally {
            ConexaoBD.closeConnection(conn);
        }
        return false;
    }
    
    public Usuario buscarUsuario(String email, String senha) {
        String sql = "SELECT * FROM tb_Usuario WHERE email = ? AND senha = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null; 

        try {
            conn = ConexaoBD.getConnection(); // Obtém a conexão do banco de dados

            
            if (conn == null) {
                return null;
            }

            stmt = conn.prepareStatement(sql); 
            stmt.setString(1, email);        
            stmt.setString(2, senha);         

            rs = stmt.executeQuery(); 

            
            if (rs.next()) {
                usuario = new Usuario(); 

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

               
                java.sql.Date sqlDate = rs.getDate("data_nascimento");
                if (sqlDate != null) {
                    usuario.setDataNascimento(sqlDate.toLocalDate());
                }

                usuario.setSenha(rs.getString("senha"));

                
                Timestamp timestamp = rs.getTimestamp("log_data_inclusao");
                if (timestamp != null) { 
                    usuario.setLogDataInclusao(timestamp.toLocalDateTime());
                }
            }
            

        } catch (SQLException e) {
            return null; 
        } finally {   
            ConexaoBD.closeConnection(conn); 
        }
        return usuario; 
    }
    
     public Usuario getPrimeiroUsuario() {
        String sql = "SELECT id, nome, email, data_nascimento, senha, log_data_inclusao FROM tb_Usuario ORDER BY id ASC LIMIT 1";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = ConexaoBD.getConnection();
            if (conn == null) {
                return null;
            }

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                
                java.sql.Date sqlDate = rs.getDate("data_nascimento");
                if (sqlDate != null) {
                    usuario.setDataNascimento(sqlDate.toLocalDate());
                }
                usuario.setSenha(rs.getString("senha"));

                Timestamp timestamp = rs.getTimestamp("log_data_inclusao");
                if (timestamp != null) {
                    usuario.setLogDataInclusao(timestamp.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o primeiro usuário: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(conn);
        }
        return usuario;
    }
    
}
