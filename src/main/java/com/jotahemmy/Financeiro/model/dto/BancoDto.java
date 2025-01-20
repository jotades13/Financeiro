package com.jotahemmy.Financeiro.model.dto;

import com.jotahemmy.Financeiro.model.entidades.Banco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BancoDto {
  private Long codigo;
  private String banco;
  private String nome;
  private String ispb;
  private String usuario;
  
  public BancoDto(Banco b){
    codigo = b.getCodigo();
    banco = b.getBanco();
    nome = b.getNome();
    ispb = b.getIspb();
    usuario = b.getUsuario();
  }
}
