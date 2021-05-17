package com.pi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pi.R;
import com.pi.model.Peca;

import java.text.DecimalFormat;

public class ShowActivity extends AppCompatActivity {

    private TextView nomeTextView;
    private TextView codigoTextView;
    private TextView categoriaTextView;
    private TextView valorVendaTextView;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        nomeTextView = findViewById(R.id.nome_textView);
        codigoTextView = findViewById(R.id.codigo_textView);
        categoriaTextView = findViewById(R.id.categoria_textView);
        valorVendaTextView = findViewById(R.id.valorVenda_textView);
        statusTextView = findViewById(R.id.status_textView);

        Intent intent = getIntent();
        Peca peca = (Peca) intent.getSerializableExtra(MainActivity.EXTRA_SHOW);
        nomeTextView.setText(peca.getNome());
        codigoTextView.setText(peca.getCodigo());
        categoriaTextView.setText(peca.getCategoria());
        valorVendaTextView.setText(new DecimalFormat("0.##").format(peca.getValorVenda()).replace(".", ","));
        statusTextView.setText(peca.isAtiva() ? "Ativa" : "Desativa");
    }
}