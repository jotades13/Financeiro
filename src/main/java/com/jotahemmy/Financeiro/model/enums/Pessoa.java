package com.jotahemmy.Financeiro.model.enums;

public enum Pessoa {

    FISICA, JURIDICA;

    public static Pessoa buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch(valor) {

            case "FÍSICA":
                return Pessoa.FISICA;

            case "JURÍDICA":
                return Pessoa.JURIDICA;

            default:
                return null;

        }

    }

}
