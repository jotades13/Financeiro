package com.jotahemmy.Financeiro.model.enums;

public enum Tipo {

    ESCALA, PROCEDIMENTO, PRIVADO;

    public static Tipo buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch (valor) {
            case "ESCALA":
                return Tipo.ESCALA;
            case "PROCEDIMENTO":
                return Tipo.PROCEDIMENTO;
            case "PRIVADO":
                return Tipo.PRIVADO;
            default:
                return null;

        }

    }

}
