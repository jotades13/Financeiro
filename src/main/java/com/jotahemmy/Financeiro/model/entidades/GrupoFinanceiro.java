package com.jotahemmy.Financeiro.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity 
@Table(name="tb_grupofinanceiro")
public class GrupoFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gpf_codigo")
    private Long codigo;
    @Column(name="gpf_descricao")
    private String descricao; 
}
