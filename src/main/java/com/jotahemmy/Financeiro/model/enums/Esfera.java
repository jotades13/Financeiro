package com.jotahemmy.Financeiro.model.enums;

public enum Esfera {

    FEDERAL, ESTADUAL, MUNICIPAL, PARTICULAR;

    public static Esfera buscarPorString(String valor) {

        valor = valor.toUpperCase();

        switch (valor) {
            case "FEDERAL":
                return Esfera.FEDERAL;
            case "ESTADUAL":
                return Esfera.ESTADUAL;
            case "MUNICIPAL":
                return Esfera.MUNICIPAL;
            case "PARTICULAR":
                return Esfera.PARTICULAR;
            default:
                return null;
        }

    }

}
