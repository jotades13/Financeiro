package com.jotahemmy.Financeiro.model.dto;

import com.jotahemmy.Financeiro.model.entidades.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDto {
  private Long codigo;
  private String nome;
  private String senha;
  private String funcao;
  private Boolean refazSenha;

  public UsuarioDto(Usuario usu){
    codigo = usu.getCodigo();
    nome = usu.getNome();
    senha = usu.getSenha();
    funcao = usu.getFuncao();
    refazSenha = usu.getRefazSenha();
  }
}
