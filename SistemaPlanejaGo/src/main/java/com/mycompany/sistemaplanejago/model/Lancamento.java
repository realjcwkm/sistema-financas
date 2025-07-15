package com.mycompany.sistemaplanejago.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lancamento {
    private int id; 
    private String descricao; 
    private BigDecimal valor; 
    private boolean statusPago; 
    private int categoria; 
    private LocalDate dataCriacao; 
    private LocalDate dataVencimento; 
    private int frequencia; 
    private int tipo; 
    private LocalDateTime logDataInclusao; 
    private LocalDateTime logDataAlteracao; 
    private int logVersaoRegistro;
    private int usuarioId; 
    private Integer centroCusto; 

    public Lancamento() {
    }

    public Lancamento(int id, String descricao, BigDecimal valor, boolean statusPago, int categoria,
                      LocalDate dataCriacao, LocalDate dataVencimento, int frequencia, int tipo,
                      LocalDateTime logDataInclusao, LocalDateTime logDataAlteracao, int logVersaoRegistro,
                      int usuarioId, Integer centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.statusPago = statusPago;
        this.categoria = categoria;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
        this.frequencia = frequencia;
        this.tipo = tipo;
        this.logDataInclusao = logDataInclusao;
        this.logDataAlteracao = logDataAlteracao;
        this.logVersaoRegistro = logVersaoRegistro;
        this.usuarioId = usuarioId;
        this.centroCusto = centroCusto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isStatusPago() { 
        return statusPago;
    }

    public void setStatusPago(boolean statusPago) {
        this.statusPago = statusPago;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getLogDataInclusao() {
        return logDataInclusao;
    }

    public void setLogDataInclusao(LocalDateTime logDataInclusao) {
        this.logDataInclusao = logDataInclusao;
    }

    public LocalDateTime getLogDataAlteracao() {
        return logDataAlteracao;
    }

    public void setLogDataAlteracao(LocalDateTime logDataAlteracao) {
        this.logDataAlteracao = logDataAlteracao;
    }

    public int getLogVersaoRegistro() {
        return logVersaoRegistro;
    }

    public void setLogVersaoRegistro(int logVersaoRegistro) {
        this.logVersaoRegistro = logVersaoRegistro;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(Integer centroCusto) {
        this.centroCusto = centroCusto;
    }

}
