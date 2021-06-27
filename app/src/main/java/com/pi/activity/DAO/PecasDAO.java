package com.pi.activity.DAO;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.pi.adapter.PecaAdapter;
import com.pi.model.Peca;

import java.util.List;

public class PecasDAO {
    public void RemoveItem(final int position, Peca peca) {

        REMOVE *

    }
    public void AdicionaItem(List<Peca> myDataSet,Peca peca){
        myDataSet.add(peca);
    }
    public void EditaItem(Peca peca_data,Peca peca2){
        peca_data.setTudo(peca2.getNome(),peca2.getCodigo(),peca2.getCategoria(),100,peca2.isAtiva());
    }

}
