package com.jotahemmy.Financeiro.model.dto;

import com.jotahemmy.Financeiro.model.entidades.Grupo;
import lombok.Getter;

@Getter
public class GrupoDto {
  
  private Long codigo;
  private String descricao;
  private String operacao;

  public GrupoDto(Grupo g){
    codigo = g.getCodigo();
    descricao = g.getDescricao();
    operacao = g.getOperacao();
  }
}
