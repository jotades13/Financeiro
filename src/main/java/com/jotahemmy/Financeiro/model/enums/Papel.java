package com.jotahemmy.Financeiro.model.enums;

public enum Papel {

    SEM_ACESSO("Sem acesso"), APENAS_VER("Apenas ver"), VER_E_GERAR_RELATORIOS("Ver e Gerar Relatórios"),
    VER_GERENCIAR("ver e Gerenciar");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
