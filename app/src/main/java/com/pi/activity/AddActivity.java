package com.pi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pi.R;
import com.pi.model.Peca;
import com.pi.model.Preco;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_CONTACT = "EXTRA_NEW_CONTACT";
    private EditText name_editText;
    private EditText codigo_editText;
    private EditText categoria_editText;
    private EditText custo_editText;
    private EditText lucro_editText;
    private Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_editText = findViewById(R.id.name_editText);
        codigo_editText = findViewById(R.id.codigo_editText);
        categoria_editText = findViewById(R.id.categoria_editText);
        custo_editText = findViewById(R.id.cust_editText);
        lucro_editText = findViewById(R.id.lucr_editText);
//        status_editText = findViewById(R.id.status_editText);
        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = name_editText.getText().toString();
                final String codigo = codigo_editText.getText().toString();
                final String categoria = categoria_editText.getText().toString();
                final double lucro = Double.parseDouble(lucro_editText.getText().toString().replace(",", "."));
                final double custo =Double.parseDouble(custo_editText.getText().toString().replace(",", "."));
                final boolean status = true;

                if (name.length() == 0) {
                    name_editText.requestFocus();
                    name_editText.setError("FIELD CANNOT BE EMPTY");
                } else if (codigo.length() == 0) {
                    codigo_editText.requestFocus();
                    codigo_editText.setError("FIELD CANNOT BE EMPTY");
                } else if (categoria.length() == 0) {
                    categoria_editText.requestFocus();
                    categoria_editText.setError("FIELD CANNOT BE EMPTY");
                } /*else if (valorVenda_editText.length() == 0) {
                    valorVenda_editText.requestFocus();
                    valorVenda_editText.setError("FIELD CANNOT BE EMPTY");
                } */else {
                    Intent data = new Intent();

                    Date dataAtual=new Date(System.currentTimeMillis());
                    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    String dataFormatada = dateFormat.format(dataAtual);

                    Preco preco8=new Preco(custo,lucro,dataFormatada);

                    Peca peca = new Peca(name, codigo, categoria, status,preco8);
                    data.putExtra(EXTRA_NEW_CONTACT, peca);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}