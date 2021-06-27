package com.pi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.R;
import com.pi.adapter.PrecoAdapter;
import com.pi.model.Preco;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrecoActivity extends AppCompatActivity implements PrecoAdapter.OnItemClickListener{
    public static final String CONT = "CONT";
    public static final String PREC = "PREC";
    public static final int PRECO_REQUEST = 6;
    public static ArrayList<Preco> precoos;
    private RecyclerView recyclerView;
    private PrecoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editTextDate;
    private EditText custo_editText;
    private EditText lucro_editText;
    private EditText preco_editText;
    private Button button_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos);
        button_salvar=findViewById(R.id.salvar_button);
        custo_editText = findViewById(R.id.custo_editText);
        lucro_editText = findViewById(R.id.lucro_editText);
        preco_editText = findViewById(R.id.preco_editText);

        custo_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(lucro_editText.getText().toString().length()!=0 && custo_editText.getText().toString().length()!=0){
                        double l=Double.parseDouble(lucro_editText.getText().toString());
                        double c=Double.parseDouble(custo_editText.getText().toString());
                        preco_editText.setText(Double.toString(c*(l/100)+c));
                    }
                }
            }
        });
        lucro_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(custo_editText.getText().toString().length()!=0 && lucro_editText.getText().toString().length()!=0){
                        double l=Double.parseDouble(lucro_editText.getText().toString());
                        double c=Double.parseDouble(custo_editText.getText().toString());
                        preco_editText.setText(Double.toString(c*(l/100)+c));
                    }
                }
            }
        });
        editTextDate = findViewById(R.id.editTextDate);
        Date dataAtual=new Date(System.currentTimeMillis());
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(dataAtual);
        editTextDate.setText(dataFormatada);
        ArrayList<Preco> precoo=PrecoActivity.precoos;
        recyclerView = findViewById(R.id.precos_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PrecoAdapter(precoo, this);
        recyclerView.setAdapter(adapter);
        //Log.d("abriu","precos");
        button_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String custo = custo_editText.getText().toString();
                final String lucro = lucro_editText.getText().toString();
                final String preco = preco_editText.getText().toString();
                final String data = editTextDate.getText().toString();
                //Preco prec=new Preco(Double.parseDouble(custo),Double.parseDouble(lucro),data);
                //Intent i = new Intent();
                //Preco preco8=new Preco(300,0.5,"25/06/2021");
                //ArrayList<Preco> precos1= new ArrayList<Preco>();
                //precos1.add(preco8);
                //Peca peca = new Peca(name, codigo, categoria, valorVenda, status/*,precos1*/);
                //data.putExtra(EXTRA_NEW_CONTACT, peca);
                //setResult(RESULT_OK, i);
                //finish();


                Intent in=getIntent();
                int ccont=(int) in.getSerializableExtra(ShowActivity.CONTAGEM);
                Intent intent = new Intent();


                //ArrayList<Preco> precoos = myDataSet.get(position).getPrecos();
                //intent.putExtra(EXTRA_PRECOS,myDataSet.get(position).getPrecos());
                //ShowActivity.precoos=precoos;
                //Peca pecaa=new Peca(myDataSet.get(position).getNome(),myDataSet.get(position).getCodigo(),myDataSet.get(position).getCategoria(),myDataSet.get(position).getValorVenda(),myDataSet.get(position).isAtiva());
                //intent.putExtra(EXTRA_SHOW, pecaa);
                //Log.d(Integer.toString(position), "terminar");
                //intent.putExtra(CONTAGEM_FINAL,position);

                Preco prec=new Preco(Double.parseDouble(custo),Double.parseDouble(lucro),data);
                precoo.add(prec);
                //Log.d(Integer.toString(ccont),"teste");
                intent.putExtra(PREC,prec);
                intent.putExtra(CONT,ccont);
                setResult(RESULT_OK, intent);
                adapter.notifyDataSetChanged();

                finish();

                /*
                if (name.length() == 0) {
                    nomeTextView.requestFocus();
                    nomeTextView.setError("FIELD CANNOT BE EMPTY");
                } else if (codigo.length() == 0) {
                    codigoTextView.requestFocus();
                    codigoTextView.setError("FIELD CANNOT BE EMPTY");
                } else if (categoria.length() == 0) {
                    categoriaTextView.requestFocus();
                    categoriaTextView.setError("FIELD CANNOT BE EMPTY");
                } else if (valorVendaTextView.length() == 0) {
                    valorVendaTextView.requestFocus();
                    valorVendaTextView.setError("FIELD CANNOT BE EMPTY");
                } else {
                    Intent data = new Intent();
                    Preco preco8=new Preco(300,0.5,"25/06/2021");
                    ArrayList<Preco> precos1= new ArrayList<Preco>();
                    precos1.add(preco8);
                    Peca peca2 = new Peca(name, codigo, categoria, valorVenda, status);
                    data.putExtra(CONTAGEM, teste);
                    data.putExtra(EXTRA_EDIT_PIECE, peca);
                    data.putExtra(EXTRA_EDIT_PIECE2, peca2);
                    setResult(RESULT_OK, data);
                    finish();
                }*/
            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }
}
