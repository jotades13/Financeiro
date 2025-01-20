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
@Table(name="tb_grupos")
public class Grupo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="grp_codigo")
  private Long codigo;
  @Column(name="grp_descricao")
  private String descricao;
  @Column(name="grp_operacao")
  private String operacao;
  @Column(name="grp_usuario")
  private String usuario;
  //@ManyToOne(fetch = FetchType.LAZY)
  //private Lancamentos lancamento;
}
