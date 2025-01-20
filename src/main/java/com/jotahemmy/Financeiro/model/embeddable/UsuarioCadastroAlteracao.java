package com.jotahemmy.Financeiro.model.embeddable;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class UsuarioCadastroAlteracao {
  
  @Column(name = "usu_data_cadastro")
  //@JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataCadastro;

  @Column(name = "usu_cadastro")
  private String usuCadastro;

  @Column(name = "usu_data_alteracao")
  //@JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAlteracao;

  @Column(name = "usu_alteracao")
  private String usuAlteracao;

}
