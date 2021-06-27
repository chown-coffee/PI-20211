package com.pi.model;

import java.io.Serializable;

public class Preco implements Serializable {
    private double custo,lucro;
    private String data;

    public double getCusto() {
        return custo;
    }

    public double getLucro() {
        return lucro;
    }

    public String getData() {
        return data;
    }

    public Preco(double custo, double lucro, String data) {
        this.custo = custo;
        this.lucro = lucro;
        this.data=data;
    }
    public double calcula_preco(){
        return custo * (lucro/100) + custo;
    }
}
