package com.pi.adapter;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.R;
import com.pi.model.Peca;
import com.pi.model.Preco;

import java.util.ArrayList;
import java.util.List;

public class PecaAdapter extends RecyclerView.Adapter<PecaAdapter.MyViewHolder> implements Filterable{

    private List<Peca> dataSet;
    private OnItemClickListener listener;
    private List<Peca> dataSetFull;
    /*private List<String> nomeList;
    private List<String> nomeListAll;*/

    public PecaAdapter(List<Peca> dataSet, OnItemClickListener listener, List<String> nomeList) {
        this.dataSet = dataSet;
        this.listener = listener;
        this.dataSetFull=new ArrayList<>(dataSet);
        //this.nomeList=nomeList;
        //this.nomeListAll=new ArrayList<>(nomeList);
    }

    public void setDataSetFull(List<Peca> dataSetFull) {
        this.dataSetFull = dataSetFull;
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
        holder.categoriaTextView.setText(peca.getCategoria());
        Preco pre=peca.getPrecos().get(peca.getPrecos().size()-1);
        holder.valorVendaTextView.setText("R$ "+pre.calcula_preco());
        if(!peca.isAtiva()){
            holder.statusImageView.setImageResource(R.drawable.ic_status_no);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Peca> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Peca item : dataSetFull){
                    if(item.getNome().toLowerCase().contains(filterPattern) || item.getCodigo().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
            /*
            if(constraint.toString().isEmpty()){
                filteredList.addAll(nomeListAll);
            }else{
                for(String nome: nomeListAll){
                    if(nome.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(nome);
                    }
                }
            }

            filteredList.add("Pneu");
            filterResults.values=filteredList;
            return filterResults;*/
            //FilterResults filterResults=new FilterResults();
            //return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d("Teste","Teste");
            dataSet.clear();
            dataSet.addAll((List) results.values);
            //nomeList.clear();
            //nomeList.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener
    {
        public TextView codigoTextView;
        public TextView nameTextView;
        public TextView categoriaTextView;
        public TextView valorVendaTextView;
        public ImageView statusImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.itemCodigo_textView);
            nameTextView = itemView.findViewById(R.id.itemName_textView);
            categoriaTextView=itemView.findViewById(R.id.itemCategoria_textView);
            valorVendaTextView=itemView.findViewById(R.id.itemValorVenda_textView);
            statusImageView=itemView.findViewById(R.id.itemStatus_imageView);
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
