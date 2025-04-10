package com.jotahemmy.Financeiro.model.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity 
@Table(name="tb_lancamentofinanceiro")
public class LancamentoFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fin_sequencia")
    private Long sequencia;
    @Column(name="fin_descricao")
    private String descricao;
    @Column(name="fin_valor")
    private BigDecimal valor;
    @Column(name="fin_lancamento")
    private LocalDate lancamento;
    @ManyToOne
    @JoinColumn(name="gpf_codigo")
    private GrupoFinanceiro grupoFinanceiro;
    @Column(name="fin_usuariocadastro")
    private String usuariocadastro;
    @Column(name="fin_datacadastro")
    private LocalDate datacadastro;
}
