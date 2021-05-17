package com.pi.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.R;
import com.pi.model.Peca;

import java.util.List;

public class ContatoAdapter  extends RecyclerView.Adapter<ContatoAdapter.MyViewHolder> {

    private List<Peca> dataSet;
    private OnItemClickListener listener;

    public ContatoAdapter(List<Peca> dataSet, OnItemClickListener listener) {
        this.dataSet = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Peca peca = dataSet.get(position);
        holder.codigoTextView.setText(peca.getCodigo());
        holder.nameTextView.setText(peca.getNome());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener
    {
        public TextView codigoTextView;
        public TextView nameTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.itemCodigo_textView);
            nameTextView = itemView.findViewById(R.id.itemName_textView);
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
