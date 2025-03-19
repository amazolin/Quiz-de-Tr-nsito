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

public class Tela1 extends AppCompatActivity {

    TextView edtBemVindo;
    RadioGroup rGroup;
    RadioButton radioB1, radioB2, radioB3, radioB4;
    Button btnPrimeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela1);

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
            Toast.makeText(Tela1.this, "Por favor, selecione uma resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String respostaEscolhida = selectedRadioButton.getText().toString();
        String respostaCorreta = "Animais selvagens";

        // Pegando a contagem de acertos da Intent anterior
        int acertos = getIntent().getIntExtra("acertos", 0);

        if (respostaEscolhida.equals(respostaCorreta)) {
            Toast.makeText(Tela1.this, "Resposta correta!", Toast.LENGTH_SHORT).show();
            acertos++;// Correção aqui
        } else {
            Toast.makeText(Tela1.this, "Resposta errada!", Toast.LENGTH_SHORT).show();
        }
        abrirTela2(acertos);

    }

    public void exibirNome() {
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome_usuario");

        if (nome != null && !nome.isEmpty()) {
            edtBemVindo.setText("Bem-vindo, " + nome + "!");
        } else {
            edtBemVindo.setText("Bem-vindo, visitante!");
        }
    }


    // Método para abrir a próxima tela
    private void abrirTela2(int acertos) {
        Intent intent = new Intent(Tela1.this, Tela3.class);
        String nome = getIntent().getStringExtra("nome_usuario"); // Pegando o nome recebido na Tela1
        intent.putExtra("nome_usuario", nome); // Enviando o nome para Tela3
        intent.putExtra("acertos", acertos);
        startActivity(intent);
        finish(); // Fecha a tela atual
    }

}
