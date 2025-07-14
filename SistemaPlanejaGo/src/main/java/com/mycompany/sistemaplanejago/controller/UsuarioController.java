package com.mycompany.sistemaplanejago.controller;

import com.mycompany.sistemaplanejago.model.Usuario;
import com.mycompany.sistemaplanejago.dao.UsuarioDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane; // Para exibir mensagens

public class UsuarioController {
    
    private UsuarioDAO usuarioDao;
    
    public UsuarioController() {
        this.usuarioDao = new UsuarioDAO(); 
    }
     
    public boolean cadastrarUsuario(String nome, String email, String dataNascimentoStr, String senha) {
            
        if (nome == null || nome.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            dataNascimentoStr == null || dataNascimentoStr.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty()) {
            //modal de alerta
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return false;
        }
            
        //Muda a formatação de data -> melhorar no design futuramente
        LocalDate dataNascimento;
        try {
            
            DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            dataNascimento = LocalDate.parse(dataNascimentoStr, dataFormatada);

            if (dataNascimento.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "Data de Nascimento não pode ser no futuro.", "Data Inválida", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de Data de Nascimento inválido. Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Usuario novoUsuario = new Usuario(nome, email, dataNascimento, senha);

        return usuarioDao.criarUsuario(novoUsuario);
    }
        
    public boolean logarUsuario(String email, String senha) {
        if (email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha o email e a senha.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        
        Usuario usuarioEncontrado = usuarioDao.buscarUsuario(email, senha);

        if (usuarioEncontrado != null) { 
            //JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo(a), " + usuarioEncontrado.getNome() + "!", "Login Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            // depois deixar o modal mais bonito
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Por favor, tente novamente.", "Erro de Login", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
        
    public String getNomePrimeiroUsuario() {
        try {
            Usuario primeiroUsuario = usuarioDao.getPrimeiroUsuario();
            if (primeiroUsuario != null) {
                return primeiroUsuario.getNome();
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter nome do primeiro usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return "Usuário"; // Nome padrão 
    }    
    
}
