package com.jotahemmy.Financeiro.model.enums;

public enum TipoDocumento {

    BOLETO, NOTAFISCAL, NOTAPROMISSORIA, CARNEPAGAMENTO, RECIBO;

    public static TipoDocumento buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch (valor) {
            case "BOLETO":
                return TipoDocumento.BOLETO;
            case "NOTAFISCAL":
                return TipoDocumento.NOTAFISCAL;
            case "NOTAPROMISSORIA":
                return TipoDocumento.NOTAPROMISSORIA;
            case "CARNEPAGAMENTO":
                return TipoDocumento.CARNEPAGAMENTO;
            case "RECIBO":
                return TipoDocumento.RECIBO;
            default:
                return null;
        }
    }
}
