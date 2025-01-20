package com.jotahemmy.Financeiro.model.enums;

public enum Operacao {

    ENTRADA, SAIDA;

    public static Operacao buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch (valor) {
            case "SAIDA":
                return Operacao.SAIDA;
            case "SAÍDA":
                return Operacao.SAIDA;
            case "ENTRADA":
                return Operacao.ENTRADA;
            default:
                return null;
        }

    }

}
