package com.jotahemmy.Financeiro.model.enums;

public enum FormaPagamento {
  
      XXX,TRANSFERENCIA, BOLETO, DINHEIRO, PIX, CARTAO;
  
      public static FormaPagamento buscarPorString(String valor) {
  
          valor = valor.toUpperCase();
  
          switch (valor) {
              case "TRANSFERENCIA":
                  return FormaPagamento.TRANSFERENCIA;
              case "BOLETO":
                  return FormaPagamento.BOLETO;
              case "DINHEIRO":
                  return FormaPagamento.DINHEIRO;
              case "PIX":
                  return FormaPagamento.PIX;
              case "CARTAO":
                  return FormaPagamento.CARTAO;
              case "XXX":
                  return FormaPagamento.XXX;
              default:
                  return null;
          }
  
      }
  
  }
