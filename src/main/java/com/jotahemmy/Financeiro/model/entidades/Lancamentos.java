package com.jotahemmy.Financeiro.model.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jotahemmy.Financeiro.model.embeddable.UsuarioCadastroAlteracao;
import com.jotahemmy.Financeiro.model.enums.Contas;
import com.jotahemmy.Financeiro.model.enums.FormaPagamento;
import com.jotahemmy.Financeiro.model.enums.Status;
import com.jotahemmy.Financeiro.model.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="tb_lancamentos")
public class Lancamentos {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="lan_titulo")
  private Long titulo;
  
  @Column(name="lan_documento")
  private String documento;
  
  @Column(name="lan_descricao")
  private String descricao;
  
  @Enumerated(EnumType.STRING)
  @Column(name="lan_contas")
  private Contas contas;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "lan_status")
  private Status status;
  
  @Column(name="lan_despesafixa")
  private Boolean despesaFixa;
  
  @Column(name="lan_valor")
  private BigDecimal valorTitulo;

  @Column(name="lan_vencimento")
  //@JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate vencimento;
  
  @Column(name="lan_observacao")
  private String observacao;
  
  @Enumerated(EnumType.STRING)
  @Column(name="lan_tipodocumento")
  private TipoDocumento tipoDocumento;
 
  @ManyToOne
  @JoinColumn(name="grp_codigo")
  private Grupo grupo;

  @ManyToOne
  @JoinColumn(name="ccu_codigo")
  private CentroCusto centroCusto;

  @Column(name="lan_bxdata")
  //@JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataBaixa;
  
  @Column(name="lan_bxusu")
  private String usuarioBaixa;
  
  @Column(name="lan_bxvalor")
  private BigDecimal valorBaixa;
  
  //@JsonProperty("bancobaixa")
  @Column(name="lan_bxbanco")
  private String bancoBaixa;
  
  @Enumerated(EnumType.STRING)
  @Column(name="lan_bxforma")
  private FormaPagamento formaPagamento;
    
  /*
  * 
  @JsonIgnore
  @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = false)
  private List<Banco> banco = new ArrayList<Banco>();
  
  @JsonIgnore
  @OneToMany  (mappedBy = "lancamento",cascade = CascadeType.ALL, orphanRemoval = false)
  private List<Grupo> grupo = new ArrayList<Grupo>();
 */

  @Embedded
  private UsuarioCadastroAlteracao usuarioCadastroAlteracao;

}


