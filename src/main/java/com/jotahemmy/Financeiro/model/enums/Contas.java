package com.jotahemmy.Financeiro.model.enums;

public enum Contas {
  
  PAGAR, RECEBER;

  public static Contas buscaPorString(String valor){
    valor = valor.toUpperCase();
    switch (valor) {
      case "PAGAR":
          return Contas.PAGAR;
      case "RECEBER":
          return Contas.RECEBER;
      default:
        return null;
    }
  } 
}
