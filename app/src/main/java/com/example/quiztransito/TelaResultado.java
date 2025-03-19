package com.example.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaResultado extends AppCompatActivity {

    TextView edtResultado;
    TextView edtBemVindo;
    Button btnEncerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telaresultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtResultado = findViewById(R.id.edtResultado);
        edtBemVindo = findViewById(R.id.edtBemVindo);
        btnEncerrar = findViewById(R.id.btnEncerrar);

        exibirNome();

        // Recuperando os dados passados pela Intent
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome_usuario");
        int acertos = intent.getIntExtra("acertos", 0);
        int resultado = (acertos * 100) / 5;

        // Exibir resultado na tela
        edtResultado.setText(resultado + "%");

        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharAplicativo();
            }
        });
    }
    public void exibirNome() {
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome_usuario");

        if (nome != null && !nome.isEmpty()) {
            edtBemVindo.setText(nome); // Apenas o nome
        } else {
            edtBemVindo.setText("Visitante");
        }

    }
    public void fecharAplicativo() {
        finishAffinity();

    }

}
