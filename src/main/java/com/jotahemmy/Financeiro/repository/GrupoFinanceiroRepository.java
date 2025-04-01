package com.jotahemmy.Financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jotahemmy.Financeiro.model.entidades.GrupoFinanceiro;

public interface GrupoFinanceiroRepository extends JpaRepository<GrupoFinanceiro, Long>{
  
  @Modifying
  @Query("SELECT g FROM GrupoFinanceiro g WHERE g.descricao like %:chave% ")
  List<GrupoFinanceiro> psqPorDescricao(String chave);
    
}
