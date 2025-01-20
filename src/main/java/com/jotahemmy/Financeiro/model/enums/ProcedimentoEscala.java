package com.jotahemmy.Financeiro.model.enums;

public enum ProcedimentoEscala {

    PROCEDIMENTO, ESCALA;

    public static ProcedimentoEscala buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {

            case "PROCEDIMENTO":
                return ProcedimentoEscala.PROCEDIMENTO;
            case "ESCALA":
                return ProcedimentoEscala.ESCALA;
            default:
                return null;

        }

    }

}
