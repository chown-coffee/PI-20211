package com.pi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Peca implements Serializable {
    private String nome;
    private String codigo;
    private String categoria;
    private double valorVenda;
    private boolean ativa;
    private ArrayList<Preco> precos;

    public ArrayList<Preco> getPrecos() {
        return precos;
    }

    public Peca () {
    }
    public Peca(String nome, String codigo, String categoria, boolean ativa,Preco preco){
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.ativa=ativa;
        this.precos=new ArrayList<>();
        this.precos.add(preco);
    }

    public Peca (String nome, String codigo, String categoria, boolean ativa /*ArrayList<Preco> precos*/) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        //this.valorVenda = valorVenda;
        this.ativa = ativa;
    }
    public Peca (String nome, String codigo, String categoria, boolean ativa,ArrayList<Preco> precos) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.ativa = ativa;
        this.precos=precos;
    }

    public String getNome() {
        return nome;
    }

    public void setTudo(String nome, String codigo, String categoria, double valorVenda, boolean ativa) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.valorVenda = valorVenda;
        this.ativa = ativa;
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
