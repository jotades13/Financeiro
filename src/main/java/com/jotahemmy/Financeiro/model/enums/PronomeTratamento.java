package com.jotahemmy.Financeiro.model.enums;

public enum PronomeTratamento {

    DR("Dr."), DRA("Dra.");

    private String nome;

    PronomeTratamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
