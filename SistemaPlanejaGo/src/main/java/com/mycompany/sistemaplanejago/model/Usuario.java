package com.mycompany.sistemaplanejago.model;

import java.time.LocalDate;     // Para data_nascimento (apenas data)
import java.time.LocalDateTime; // Para log_data_inclusao (data e hora)

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;     
    private String senha;
    private LocalDateTime logDataInclusao; 

    public Usuario() {
    }

    public Usuario(String nome, String email, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public Usuario(int id, String nome, String email, LocalDate dataNascimento, String senha, LocalDateTime logDataInclusao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.logDataInclusao = logDataInclusao; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getLogDataInclusao() {
        return logDataInclusao;
    }

    public void setLogDataInclusao(LocalDateTime logDataInclusao) {
        this.logDataInclusao = logDataInclusao;
    }


}