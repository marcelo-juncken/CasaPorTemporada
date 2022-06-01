package com.example.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casaportemporada.R;
import com.example.casaportemporada.helper.FirebaseHelper;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edit_email;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        iniciaComponentes();
        configCliques();
    }

    public void validaDados(View view) {
        String email = edit_email.getText().toString().trim();

        if (!email.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            recuperarSenha(email);
        } else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu email.");
        }
    }

    private void recuperarSenha(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(
                email
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Email enviado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                String erro = task.getException().getMessage();
                Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        });
    }

    private void iniciaComponentes() {
        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Recuperar conta");

        edit_email = findViewById(R.id.edit_email);
        progressBar = findViewById(R.id.progressBar);
    }

    private void configCliques() {
        findViewById(R.id.ib_voltar).setOnClickListener(v -> {
            finish();
        });
    }
}
