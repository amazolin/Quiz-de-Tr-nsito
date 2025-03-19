package com.example.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaResultado extends AppCompatActivity {

    TextView edtResultado;
    TextView edtBemVindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtResultado = findViewById(R.id.txtResultado);
        edtBemVindo = findViewById(R.id.edtBemVindo);


        // Recuperando os dados passados pela Intent
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome_usuario");
        int acertos = intent.getIntExtra("acertos", 0);

        // Exibir resultado na tela
        edtResultado.setText(nome + ", você acertou " + acertos + " perguntas!");
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
}
