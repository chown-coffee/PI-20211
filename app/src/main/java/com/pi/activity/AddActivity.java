package com.pi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pi.R;
import com.pi.model.Peca;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_CONTACT = "EXTRA_NEW_CONTACT";
    private EditText name_editText;
    private EditText codigo_editText;
    private EditText categoria_editText;
    private EditText valorVenda_editText;
    private Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_editText = findViewById(R.id.name_editText);
        codigo_editText = findViewById(R.id.codigo_editText);
        categoria_editText = findViewById(R.id.categoria_editText);
        valorVenda_editText = findViewById(R.id.valorVenda_editText);
//        status_editText = findViewById(R.id.status_editText);
        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = name_editText.getText().toString();
                final String codigo = codigo_editText.getText().toString();
                final String categoria = categoria_editText.getText().toString();
                final double valorVenda = Double.parseDouble(valorVenda_editText.getText().toString().replace(",", "."));
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
                } else if (valorVenda_editText.length() == 0) {
                    valorVenda_editText.requestFocus();
                    valorVenda_editText.setError("FIELD CANNOT BE EMPTY");
                } else {
                    Intent data = new Intent();
                    Peca peca = new Peca(name, codigo, categoria, valorVenda, status);
                    data.putExtra(EXTRA_NEW_CONTACT, peca);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}