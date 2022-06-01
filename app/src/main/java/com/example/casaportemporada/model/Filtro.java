package com.example.casaportemporada.model;

import java.io.Serializable;

public class Filtro implements Serializable {

    private int qtd_quarto;
    private int qtd_banheiro;
    private int qtd_garagem;

    public int getQtd_quarto() {
        return qtd_quarto;
    }

    public void setQtd_quarto(int qtd_quarto) {
        this.qtd_quarto = qtd_quarto;
    }

    public int getQtd_banheiro() {
        return qtd_banheiro;
    }

    public void setQtd_banheiro(int qtd_banheiro) {
        this.qtd_banheiro = qtd_banheiro;
    }

    public int getQtd_garagem() {
        return qtd_garagem;
    }

    public void setQtd_garagem(int qtd_garagem) {
        this.qtd_garagem = qtd_garagem;
    }
}
