package com.example.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela3 extends AppCompatActivity {

    TextView edtBemVindo;
    RadioGroup rGroup;
    RadioButton radioB1, radioB2, radioB3, radioB4;
    Button btnPrimeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtBemVindo = findViewById(R.id.edtBemVindo);
        rGroup = findViewById(R.id.rGroup);
        radioB1 = findViewById(R.id.radioB1);
        radioB2 = findViewById(R.id.radioB2);
        radioB3 = findViewById(R.id.radioB3);
        radioB4 = findViewById(R.id.radioB4);
        btnPrimeira = findViewById(R.id.btnPrimeira);

        //Charmar-o método
        exibirNome();

        // Evento de clique do botão
        btnPrimeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resposta1();
            }
        });
    }

    // Método para verificar a resposta
    public void resposta1() {
        int selectedId = rGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(Tela3.this, "Por favor, selecione uma resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String respostaEscolhida = selectedRadioButton.getText().toString();
        String respostaCorreta = "Preferência"; // Resposta correta

        int acertos = getIntent().getIntExtra("acertos", 0); // Pegando acertos anteriores

        if (respostaEscolhida.equals(respostaCorreta)) {
            Toast.makeText(Tela3.this, "Resposta correta!", Toast.LENGTH_SHORT).show();
            acertos++; // Incrementa acertos
        } else {
            Toast.makeText(Tela3.this, "Resposta errada!", Toast.LENGTH_SHORT).show();
        }

        abrirTelaFinal(acertos);
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


    private void abrirTelaFinal(int acertos) {
        Intent intent = new Intent(Tela3.this, Tela4.class);
        String nome = getIntent().getStringExtra("nome_usuario");
        intent.putExtra("nome_usuario", nome);
        intent.putExtra("acertos", acertos);
        startActivity(intent);
        finish();
    }
}
