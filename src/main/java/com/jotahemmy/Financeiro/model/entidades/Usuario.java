package com.jotahemmy.Financeiro.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_usuarios")
public class Usuario {
      @Id    
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name="usu_codigo")
      private Long codigo;
      @Column(name = "usu_nome")
      private String nome;
      @Column(name = "usu_nomecompleto")
      private String nomecompleto;
      @Column(name = "usu_senha")
      private String senha;
      @Column(name="usu_funcao") 
      private String funcao;    
      @Column(name="usu_refazsenha")
      private Boolean refazSenha; 
  }
