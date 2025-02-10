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
@Table(name="tb_centrocusto")
public class CentroCusto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ccu_codigo")
  private Long codigo;
  @Column(name="ccu_descricao")
  private String descricao;
  @Column(name="ccu_ativo")
  private Boolean ativo;
  @Column(name="ccu_usuario")
  private String usuario;
}
