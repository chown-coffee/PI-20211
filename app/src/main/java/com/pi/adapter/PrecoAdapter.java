package com.pi.adapter;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.R;
import com.pi.model.Peca;
import com.pi.model.Preco;

import java.util.ArrayList;
import java.util.List;

public class PrecoAdapter extends RecyclerView.Adapter<PrecoAdapter.MyViewHolder>{

    private ArrayList<Preco> dataSet;
    private OnItemClickListener listener;
    /*private List<String> nomeList;
    private List<String> nomeListAll;*/

    public PrecoAdapter(ArrayList<Preco> dataSet, OnItemClickListener listener) {
        this.dataSet = dataSet;
        this.listener = listener;
        //this.nomeList=nomeList;
        //this.nomeListAll=new ArrayList<>(nomeList);
    }

    @NonNull
    @Override
    public PrecoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_preco_layout, parent, false);
        return new PrecoAdapter.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull PrecoAdapter.MyViewHolder holder, int position) {
        Preco preco = dataSet.get(position);
        holder.dataPreco_textView.setText("Data: "+(preco.getData()));
        holder.custo_textView.setText("Custo: "+Double.toString(preco.getCusto()));
        holder.lucro_textView.setText("Lucro: "+Double.toString(preco.getLucro()));
        holder.preco_textView.setText("Venda: "+Double.toString(preco.calcula_preco()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener
    {
        public TextView dataPreco_textView;
        public TextView custo_textView;
        public TextView lucro_textView;
        public TextView preco_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dataPreco_textView = itemView.findViewById(R.id.dataPreco_textView);
            custo_textView = itemView.findViewById(R.id.custo_textView);
            lucro_textView = itemView.findViewById(R.id.lucro_textView);
            preco_textView = itemView.findViewById(R.id.preco_textView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select an action");
            menu.add(getAdapterPosition(), 222, 0, "Remove");
//            menu.add(getAdapterPosition(), 223, 1, "Share");
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}