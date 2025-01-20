package com.jotahemmy.Financeiro.model.enums;

public enum EstadoCivil {

    SOLTEIRO("Solteiro(a)"), CASADO("Casado(a)"), VIUVO("Viúvo(a)"), DIVORCIADO("Divorciado(a)"),
    SEPARADO("Separado(a)");

    private String descricao;

    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
