package com.example.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casaportemporada.R;
import com.example.casaportemporada.activity.FormAnuncioActivity;
import com.example.casaportemporada.activity.MainActivity;
import com.example.casaportemporada.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_email;
    private EditText edit_senha;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciaComponentes();
        configCliques();

    }

    private void configCliques() {
        findViewById(R.id.text_criar_conta).setOnClickListener(v ->
                startActivity(new Intent(this, CadastroActivity.class)));
        findViewById(R.id.text_forgot_password).setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPasswordActivity.class)));
        findViewById(R.id.ib_voltar).setOnClickListener(v -> {
            finish();
        });

    }

    public void validaDados(View view) {
        String email = edit_email.getText().toString().trim();
        String senha = edit_senha.getText().toString();
        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);
                logar(email, senha);
            } else {
                edit_senha.requestFocus();
                edit_senha.setError("Informe sua senha.");
            }
        } else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu email.");
        }
    }

    private void logar(String email, String senha) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
            } else {
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }

        });

    }

    public void iniciaComponentes() {
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        progressBar = findViewById(R.id.progressBar);
    }


}