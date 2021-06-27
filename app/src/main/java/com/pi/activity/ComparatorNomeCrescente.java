package com.pi.activity;

import com.pi.model.Peca;

import java.util.Comparator;

public class ComparatorNomeCrescente implements Comparator<Peca> {

    @Override
    public int compare(Peca peca1, Peca peca2) {
        if(peca1.getNome().compareTo(peca2.getNome())>0){
            return 1;
        }
        return -1;
    }
}
