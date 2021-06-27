package com.pi.activity.DAO;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.pi.adapter.PecaAdapter;
import com.pi.model.Peca;

import java.util.List;

public class PecasDAO {
    public void RemoveItem(final int position, Context context, List<Peca> myDataSet, PecaAdapter adapter) {
        /*Logger logger=Logger.getLogger(PecasDAO.class.getName());
        System.out.println(logger.toString());*/
        Log.d("teste","teste");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Remove?");
        alertDialog.setMessage("Do you want to remove " + myDataSet.get(position).getNome() + "?");

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDataSet.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.setNegativeButton("Cancel", null);

        alertDialog.show();
    }
    public void AdicionaItem(List<Peca> myDataSet,Peca peca){
        myDataSet.add(peca);
    }
    public void EditaItem(Peca peca_data,Peca peca2){
        peca_data.setTudo(peca2.getNome(),peca2.getCodigo(),peca2.getCategoria(),100,peca2.isAtiva());
    }

}
