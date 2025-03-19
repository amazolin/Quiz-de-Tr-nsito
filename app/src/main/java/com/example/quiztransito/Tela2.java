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

        if (respostaEscolhida.equals(respostaCorreta)) {
            Toast.makeText(Tela1.this, "Resposta correta!", Toast.LENGTH_SHORT).show();
            abrirProximaTela();
        } else {
            Toast.makeText(Tela1.this, "Resposta errada, tente novamente!", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para abrir a próxima tela
    private void abrirProximaTela() {
        Intent intent = new Intent(Tela1.this, Tela2.class); // Substitua pela próxima Activity
        startActivity(intent);
        finish(); // Fecha a tela atual para não permitir voltar
    }
}