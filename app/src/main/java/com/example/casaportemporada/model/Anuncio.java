package com.example.casaportemporada.model;

import android.widget.Toast;

import com.example.casaportemporada.helper.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class Anuncio implements Serializable {

    private String id;
    private String idUsuario;
    private String titulo;
    private String descricao;
    private String quartos;
    private String banheiros;
    private String garagem;
    private boolean status;
    private String urlImagem;


    public Anuncio() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }

    public void salvar() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("anuncios")
                .child(FirebaseHelper.getIdFirebase())
                .child(this.getId());
        reference.setValue(this);


        if (this.isStatus()) {
            DatabaseReference anuncioPublico = FirebaseHelper.getDatabaseReference()
                    .child("anuncios_publicos")
                    .child(this.getId());
            anuncioPublico.setValue(this);
        } else {
            DatabaseReference referencePublico = FirebaseHelper.getDatabaseReference()
                    .child("anuncios_publicos")
                    .child(this.getId());
            referencePublico.removeValue();
        }

    }

    public void deletar() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("anuncios")
                .child(FirebaseHelper.getIdFirebase())
                .child(this.getId());
        reference.removeValue().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               StorageReference storageReference = FirebaseHelper.getStorageReference()
                       .child("imagens")
                       .child("anuncios")
                       .child(this.getId() + ".jpeg");
               storageReference.delete();

               DatabaseReference referencePublico = FirebaseHelper.getDatabaseReference()
                       .child("anuncios_publicos")
                       .child(this.getId());
               referencePublico.removeValue();
           }
        });

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuartos() {
        return quartos;
    }

    public void setQuartos(String quartos) {
        this.quartos = quartos;
    }

    public String getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(String banheiros) {
        this.banheiros = banheiros;
    }

    public String getGaragem() {
        return garagem;
    }

    public void setGaragem(String garagem) {
        this.garagem = garagem;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

}
