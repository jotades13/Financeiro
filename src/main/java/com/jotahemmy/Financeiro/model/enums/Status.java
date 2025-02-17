package com.jotahemmy.Financeiro.model.enums;

public enum Status {

    CANCELADO, BAIXADO, LANCADOS;

    public static Status buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {
            case "CANCELADO":
                return Status.CANCELADO;
            case "BAIXADO":
                return Status.BAIXADO;
            case "LANCADOS":
                return Status.LANCADOS;    
            default:
                return null;
        }
    }
}
