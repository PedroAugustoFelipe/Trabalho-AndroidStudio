package com.example.trabalho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtIdade;
    private EditText edtDisciplina;
    private EditText edtNotaUm;
    private EditText edtNotaDois;
    private Button btResetar;
    private Button btCalcular;
    private TextView TextViewResultado;
    private TextView TextViewErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtIdade = findViewById(R.id.edtIdade);
        edtDisciplina = findViewById(R.id.edtDisciplina);
        edtNotaUm = findViewById(R.id.edtNotaUm);
        edtNotaDois = findViewById(R.id.edtNotaDois);
        btCalcular = findViewById(R.id.btCalcular);
        btResetar = findViewById(R.id.btResetar);
        TextViewResultado = findViewById(R.id.TextViewResultado);
        TextViewErro = findViewById(R.id.TextViewErro);

        // Botão Calcular
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarExibirDados();
            }
        });

        // Botão Resetar
        btResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetarCampos();
            }
        });
    }

    private void ValidarExibirDados() {
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String idade = edtIdade.getText().toString();
        String disciplina = edtDisciplina.getText().toString();
        String notaum = edtNotaUm.getText().toString();
        String notadois = edtNotaDois.getText().toString();

        // Validação dos campos inseridos
        if (nome.isEmpty()) {
            TextViewErro.setText("O campo nome está vazio");
            TextViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (email.isEmpty() || !email.contains("@")) {
            TextViewErro.setText("O Email está incorreto");
            TextViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (idade.isEmpty() || Integer.parseInt(idade) <= 0) {
            TextViewErro.setText("A idade precisa ser acima ou igual a 0");
            TextViewErro.setVisibility(View.VISIBLE);
            return;
        } else if (notaum.isEmpty() || notadois.isEmpty() || !isNotaValida(notaum) || !isNotaValida(notadois)) {
            TextViewErro.setText("As notas devem estar entre 0 a 10");
            TextViewErro.setVisibility(View.VISIBLE);
            return;
        }

        // Calcular média
        double media = (Double.parseDouble(notaum) + Double.parseDouble(notadois)) / 2;

        // Exibir os dados completos
        TextViewResultado.setText("Nome: " + nome + "\nEmail: " + email + "\nIdade: " + idade +
                "\nDisciplina: " + disciplina + "\nNotas: " + notaum + " e " + notadois +
                "\nMédia: " + media + "\n" + (media >= 6 ? "Aprovado" : "Reprovado"));
        TextViewErro.setVisibility(View.GONE);
    }

    // Verificar se a nota do aluno é válida
    private boolean isNotaValida(String nota) {
        try {
            double valor = Double.parseDouble(nota);
            return valor >= 0 && valor <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Limpar os campos
    private void resetarCampos() {
        edtNome.setText("");
        edtEmail.setText("");
        edtIdade.setText("");
        edtDisciplina.setText("");
        edtNotaUm.setText("");
        edtNotaDois.setText("");
        TextViewResultado.setVisibility(View.GONE);
        TextViewErro.setVisibility(View.GONE);
    }
}
