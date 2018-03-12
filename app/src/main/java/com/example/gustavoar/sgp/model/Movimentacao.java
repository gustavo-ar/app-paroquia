package com.example.gustavoar.sgp.model;

import java.sql.Timestamp;

/**
 * Created by addmn.cassio on 12/03/2018.
 */

public class Movimentacao {
    private int descricao;
    private boolean valor;
    private int categoria;
    private Timestamp tipo;

    public int getDescricao() {
        return descricao;
    }

    public boolean getValor() {
        return valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public Timestamp getTipo() {
        return tipo;
    }
}
