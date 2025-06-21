package com.mycompany.sistemaplanejago.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/planejago"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "senha"; //cada integrante coloca a sua respectiva senha
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
    }
    
     public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
     
    // Só testar a conexão, quem precisar testar só tirar o comentário
    //public static void main(String[] args) {
    //    Connection connection = null;
    //    try {
    //        connection = ConexaoBD.getConnection();
    //        if (connection != null) {
    //            System.out.println("Conexão estabelecida com sucesso!");
    //        } else {
    //            System.out.println("Falha na conexão.");
    //        }
    //    } finally {
    //        closeConnection(connection);
    //    }
    //}
}
