package com.example.casaportemporada.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casaportemporada.R;
import com.example.casaportemporada.model.Filtro;

import org.w3c.dom.Text;

public class FiltrarAnunciosActivity extends AppCompatActivity {
    private TextView text_quarto;
    private TextView text_banheiro;
    private TextView text_garagem;
    private SeekBar sb_quarto;
    private SeekBar sb_banheiro;
    private SeekBar sb_garagem;

    private int qtd_quarto;
    private int qtd_banheiro;
    private int qtd_garagem;

    private Filtro filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar_anuncios);

        iniciaComponentes();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            filtro = (Filtro) bundle.getSerializable("filtro");
            if (filtro != null) {
                carregaFiltro();
            }
        }
        configCliques();
        configSb();

    }

    private void configSb() {
        sb_quarto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    text_quarto.setText("0 quarto ou mais");
                } else {
                    text_quarto.setText(progress + " quartos ou mais");
                }
                qtd_quarto = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sb_banheiro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    text_banheiro.setText("0 banheiro ou mais");
                } else {
                    text_banheiro.setText(progress + " banheiros ou mais");
                }
                qtd_banheiro = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sb_garagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    text_garagem.setText("0 garagem ou mais");
                } else {
                    text_garagem.setText(progress + " garagens ou mais");
                }
                qtd_garagem = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void filtrar(View view) {

        if (filtro == null) filtro = new Filtro();
        filtro.setQtd_quarto(qtd_quarto);
        filtro.setQtd_banheiro(qtd_banheiro);
        filtro.setQtd_garagem(qtd_garagem);

        Intent intent = new Intent();
        intent.putExtra("filtro", filtro);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void limparFiltro(View view) {
        qtd_quarto = 0;
        qtd_banheiro = 0;
        qtd_garagem = 0;

        filtro.setQtd_quarto(0);
        filtro.setQtd_banheiro(0);
        filtro.setQtd_garagem(0);

        finish();
    }

    private void carregaFiltro() {
        qtd_quarto = filtro.getQtd_quarto();
        qtd_banheiro = filtro.getQtd_banheiro();
        qtd_garagem = filtro.getQtd_garagem();

        sb_quarto.setProgress(qtd_quarto);
        sb_banheiro.setProgress(qtd_banheiro);
        sb_garagem.setProgress(qtd_garagem);


        if (filtro.getQtd_quarto() == 0) {
            text_quarto.setText("0 quarto ou mais");
        } else {
            text_quarto.setText(qtd_quarto + " quartos ou mais");
        }

        if (filtro.getQtd_banheiro() == 0) {
            text_banheiro.setText("0 banheiro ou mais");
        } else {
            text_banheiro.setText(qtd_banheiro + " banheiros ou mais");
        }

        if (filtro.getQtd_garagem() == 0) {
            text_garagem.setText("0 garagem ou mais");
        } else {
            text_garagem.setText(qtd_garagem + " garagens ou mais");
        }

    }

    private void iniciaComponentes() {
        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Filtrar");

        text_quarto = findViewById(R.id.text_quarto);
        text_banheiro = findViewById(R.id.text_banheiro);
        text_garagem = findViewById(R.id.text_garagem);
        sb_quarto = findViewById(R.id.sb_quarto);
        sb_banheiro = findViewById(R.id.sb_banheiro);
        sb_garagem = findViewById(R.id.sb_garagem);
    }

    private void configCliques() {
        findViewById(R.id.ib_voltar).setOnClickListener(v -> {
            finish();
        });

    }

}