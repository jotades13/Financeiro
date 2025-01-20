package com.jotahemmy.Financeiro.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name="tb_log")
public class Log {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="log_sequencia")
  private long sequencia;
  @Column(name="log_modulo")
  private String modulo;
  @Column(name="log_detalhamento")
  private String detalhamento;
  @Column(name="log_chave")
  private Long chave;
  @Column(name="log_lancamento")
  private Timestamp lancamento;
}
