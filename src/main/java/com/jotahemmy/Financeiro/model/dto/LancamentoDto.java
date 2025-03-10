package com.jotahemmy.Financeiro.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jotahemmy.Financeiro.model.entidades.Lancamentos;
import com.jotahemmy.Financeiro.model.enums.Contas;
import com.jotahemmy.Financeiro.model.enums.Status;
import com.jotahemmy.Financeiro.model.enums.TipoDocumento;
import lombok.Getter;

@Getter
public class LancamentoDto {
  private Long titulo;
  private String documento;
  private String descricao;
  private Contas contas;
  private Status status;
  private BigDecimal valorTitulo;
  private LocalDate vencimento;
  private TipoDocumento tipoDocumento;
  private String grupo;
  private String centroCusto;
  private String mesAnoVencimento;
  private LocalDate lancadoEm;
  private String lancadoPor;

  public LancamentoDto(Lancamentos l){
    titulo = l.getTitulo();
    documento = l.getDocumento();
    descricao = l.getDescricao();
    contas = l.getContas();
    status = l.getStatus();
    valorTitulo = l.getValorTitulo();
    vencimento = l.getVencimento();
    tipoDocumento = l.getTipoDocumento();
    grupo = (l.getGrupo() != null) ? l.getGrupo().getDescricao() : "Sem Registro";
    centroCusto = (l.getCentroCusto() != null) ? l.getCentroCusto().getDescricao() : "Sem Registro";
    mesAnoVencimento = l.getVencimento().getMonthValue()+"/"+l.getVencimento().getYear();
    lancadoEm = l.getUsuarioCadastroAlteracao().getDataCadastro();
    lancadoPor = l.getUsuarioCadastroAlteracao().getUsuCadastro();
  }

}
