package com.jotahemmy.Financeiro.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jotahemmy.Financeiro.model.embeddable.UsuarioCadastroAlteracao;
import com.jotahemmy.Financeiro.model.entidades.CentroCusto;
import com.jotahemmy.Financeiro.model.entidades.Grupo;
import com.jotahemmy.Financeiro.model.entidades.Lancamentos;
import com.jotahemmy.Financeiro.model.enums.Contas;
import com.jotahemmy.Financeiro.model.enums.FormaPagamento;
import com.jotahemmy.Financeiro.model.enums.Status;
import com.jotahemmy.Financeiro.model.enums.TipoDocumento;
import lombok.Getter;

@Getter
public class LancamentoDto {
  private Long titulo;
  private String descricao;
  private Contas contas;
  private Status status;
  private Boolean despesaFixa;
  private BigDecimal valorTitulo;
  private LocalDate vencimento;
  private String observacao;
  private TipoDocumento tipoDocumento;
  private Grupo grupo;
  private CentroCusto centroCusto;
  private LocalDate dataBaixa;
  private String usuarioBaixa;
  private BigDecimal valorBaixa;
  private String bancoBaixa;
  private FormaPagamento formaPagamento;
  private UsuarioCadastroAlteracao usuarioCadastroAlteracao;

  public LancamentoDto(Lancamentos l){
    titulo = l.getTitulo();
    descricao = l.getDescricao();
    contas = l.getContas();
    status = l.getStatus();
    despesaFixa = l.getDespesaFixa();
    valorTitulo = l.getValorTitulo();
    vencimento = l.getVencimento();
    observacao = l.getObservacao();
    tipoDocumento = l.getTipoDocumento();
    grupo = l.getGrupo();
    centroCusto = l.getCentroCusto();
    dataBaixa = l.getDataBaixa();
    usuarioBaixa = l.getUsuarioBaixa();
    valorBaixa = l.getValorBaixa();
    bancoBaixa = l.getBancoBaixa();
    formaPagamento = l.getFormaPagamento();
    usuarioCadastroAlteracao = l.getUsuarioCadastroAlteracao();
  }

}
