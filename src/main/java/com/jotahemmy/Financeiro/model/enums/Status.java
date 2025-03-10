package com.jotahemmy.Financeiro.model.enums;

public enum Status {

    EMABERTO, BAIXADO;

    public static Status buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {
            case "EMABERTO":
                return Status.EMABERTO;
            case "BAIXADO":
                return Status.BAIXADO;
            default:
                return null;
        }
    }
}
