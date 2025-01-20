package com.jotahemmy.Financeiro.model.enums;

public enum TipoContratacao {

    CONTRATO, OFICIO, EMERGENCIAL;

    public static TipoContratacao buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {

            case "CONTRATO":
                return TipoContratacao.CONTRATO;
            case "OFICIO":
                return TipoContratacao.OFICIO;
            case "EMERGENCIAL":
                return TipoContratacao.EMERGENCIAL;
            default:
                return null;
        }

    }

}
