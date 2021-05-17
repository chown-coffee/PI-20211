package com.pi.model;

import java.io.Serializable;

public class Peca implements Serializable {
    private String nome;
    private String codigo;
    private String categoria;
    private double valorVenda;
    private boolean ativa;

    public Peca () {
    }

    public Peca (String nome, String codigo, String categoria, double valorVenda, boolean ativa) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.valorVenda = valorVenda;
        this.ativa = ativa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
