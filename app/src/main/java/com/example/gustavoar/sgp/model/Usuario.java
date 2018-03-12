package com.example.gustavoar.sgp.model;

import com.example.gustavoar.sgp.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

/**
 * Created by GUSTAVOAR on 04/03/2018.
 */

public class Usuario {
    private String idUsuario;
    private String nome;
    private String email;
    private String senha;

    //retirar esses 3 atributos
    private Double receitaTotal = 0.00;
    private Double despesaTotal = 0.00;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("usuarios")
                .child( this.idUsuario )
                .setValue( this );
    }

    //deletar essas quatro linhas
    public Double getReceitaTotal() {
        return receitaTotal;
    }
    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }
    public Double getDespesaTotal() {
        return despesaTotal;
    }
    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}