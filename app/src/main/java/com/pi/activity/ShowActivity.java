package com.pi.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pi.R;
import com.pi.adapter.PrecoAdapter;
import com.pi.model.Peca;
import com.pi.model.Preco;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity implements PrecoAdapter.OnItemClickListener {
    public static final String EXTRA_EDIT_PIECE = "EXTRA_EDIT_PIECE";
    public static final String EXTRA_EDIT_PIECE2 = "EXTRA_EDIT_PIECE2";
    public static final String CONTAGEM ="CONTAGEM";
    private static final int MAIN_REQUEST = 3;
    private static final int PRECO_REQUEST = 4;
    private static final int PRECC=1;
    public static ArrayList<Preco> precoos;
    private int contt;
    /*private TextView nomeTextView;
    private TextView codigoTextView;
    private TextView categoriaTextView;
    private TextView valorVendaTextView;
    private TextView statusTextView;*/
    private EditText nomeTextView;
    private EditText codigoTextView;
    private EditText categoriaTextView;
    private EditText valorVendaTextView;
    private TextView custo_textView;
    private TextView lucro_textView;
    private TextView venda_textView;
    private Button edit_button;
    private ImageView image_button;
    private SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ArrayList<Preco> precoos = ShowActivity.precoos;

        image_button=findViewById(R.id.image_preco);

        nomeTextView = findViewById(R.id.nome_textView);
        codigoTextView = findViewById(R.id.codigo_textView);
        categoriaTextView = findViewById(R.id.categoria_textView);
        //valorVendaTextView = findViewById(R.id.valorVenda_textView);
        switchCompat = findViewById(R.id.status_textView);
        custo_textView = findViewById(R.id.custo_textView);
        lucro_textView = findViewById(R.id.lucro_textView);
        venda_textView = findViewById(R.id.venda_textView);

        Intent intent = getIntent();
        //image_button.setBackgroundColor(getContext().getResources().getColor(R.color.black));
        Peca peca = (Peca) intent.getSerializableExtra(MainActivity.EXTRA_SHOW);
        int teste = (int) intent.getSerializableExtra(MainActivity.CONTAGEM_FINAL);
        nomeTextView.setText(peca.getNome());
        codigoTextView.setText(peca.getCodigo());
        categoriaTextView.setText(peca.getCategoria());
        //valorVendaTextView.setText(new DecimalFormat("0.##").format(peca.getValorVenda()).replace(".", ","));
        switchCompat.setText(peca.isAtiva() ? "Ativado" : "Desativado");
        switchCompat.setChecked(peca.isAtiva() ? true : false);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchCompat.setText("Ativa");
                    switchCompat.setChecked(true);
                }else{
                    switchCompat.setText("Desativa");
                    switchCompat.setChecked(false);
                }
            }
        });
        int tamanho=precoos.size() - 1;

            custo_textView.setText("Custo: "+Double.toString(precoos.get(tamanho).getCusto()));
            lucro_textView.setText("Lucro: "+Double.toString(precoos.get(tamanho).getLucro()));
            venda_textView.setText("Venda: "+Double.toString(precoos.get(tamanho).calcula_preco()));

        edit_button = findViewById(R.id.edit_button);

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String name = nomeTextView.getText().toString();
                //final String codigo = codigoTextView.getText().toString();
                //final String categoria = categoriaTextView.getText().toString();
                //final double valorVenda = Double.parseDouble(valorVendaTextView.getText().toString().replace(",", "."));
                //final boolean status = true;
                  //  Peca peca2 = new Peca(name, codigo, categoria, valorVenda, status);

                PrecoActivity.precoos=precoos;
                Intent data = new Intent(ShowActivity.this,PrecoActivity.class);
                //data.putExtra(CONTAGEM, teste);
                data.putExtra(CONTAGEM, teste);/*
                data.putExtra(EXTRA_EDIT_PIECE2, peca2);*/
                    startActivityForResult(data,PRECO_REQUEST);
                    setResult(RESULT_OK, data);
                    //finish();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nomeTextView.getText().toString();
                final String codigo = codigoTextView.getText().toString();
                final String categoria = categoriaTextView.getText().toString();
                //final double valorVenda = Double.parseDouble(valorVendaTextView.getText().toString().replace(",", "."));
                final boolean status;
                if(switchCompat.isChecked()){
                    status=true;
                }else{
                    status=false;
                }

                if (name.length() == 0) {
                    nomeTextView.requestFocus();
                    nomeTextView.setError("FIELD CANNOT BE EMPTY");
                } else if (codigo.length() == 0) {
                    codigoTextView.requestFocus();
                    codigoTextView.setError("FIELD CANNOT BE EMPTY");
                } else if (categoria.length() == 0) {
                    categoriaTextView.requestFocus();
                    categoriaTextView.setError("FIELD CANNOT BE EMPTY");
                } /*else if (valorVendaTextView.length() == 0) {
                    valorVendaTextView.requestFocus();
                    valorVendaTextView.setError("FIELD CANNOT BE EMPTY");
                } */else {
                    Intent data = new Intent();
                    Preco preco8=new Preco(300,0.5,"25/06/2021");
                    //ArrayList<Preco> precos1= new ArrayList<Preco>();
                    //precos1.add(preco8);
                    Peca peca2 = new Peca(name, codigo, categoria, status);
                    data.putExtra(CONTAGEM, teste);
                    data.putExtra(EXTRA_EDIT_PIECE, peca);
                    data.putExtra(EXTRA_EDIT_PIECE2, peca2);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        ShowActivity.precoos=null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PRECO_REQUEST) {
            if (resultCode == RESULT_OK) {
                Preco pre = (Preco) data.getSerializableExtra(PrecoActivity.PREC);
                custo_textView.setText("Custo: "+Double.toString(pre.getCusto()));
                lucro_textView.setText("Lucro: "+Double.toString(pre.getLucro()));
                venda_textView.setText("Venda: "+Double.toString(pre.calcula_preco()));
            }
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}