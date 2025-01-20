package com.jotahemmy.Financeiro.model.enums;

public enum Situacao {

    ABERTA, FECHADA, EM_PROCESSAMENTO;

    public static Situacao buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {
            case "ABERTA":
                return Situacao.ABERTA;
            case "FECHADA":
                return Situacao.FECHADA;
            case "EM PROCESSAMENTO":
                return Situacao.EM_PROCESSAMENTO;
            default:
                return null;
        }
    }

}
