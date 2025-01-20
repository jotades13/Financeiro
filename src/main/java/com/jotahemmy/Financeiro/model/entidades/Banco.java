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
@Table(name = "tb_bancos")
public class Banco {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bnc_codigo")
    private Long codigo;
    @Column(name = "bnc_banco")
    private String banco;
    @Column(name = "bnc_nome")
    private String nome;
    @Column(name = "bnc_ispb")
    private String ispb;
    @Column(name="bnc_usuario")
    private String usuario;
 /*
 @ManyToOne(fetch = FetchType.LAZY)
 private Lancamentos lancamento; 
 */ 
  
}
