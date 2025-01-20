package com.jotahemmy.Financeiro.model.enums;

public enum SituacaoCadastral {

    COOPERADO("Cooperado"), DEMITIDO("Demitido"), FALECIDO("Falecido"), TEMPORARIO("Temporário"),
    TRANSFERIDO("Transferido");

    private String descricao;

    SituacaoCadastral(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
