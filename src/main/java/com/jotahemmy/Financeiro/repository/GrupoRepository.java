package com.jotahemmy.Financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jotahemmy.Financeiro.model.entidades.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
  
  @Modifying
  @Query("SELECT g FROM Grupo g WHERE g.operacao like %:chave% ")
  List<Grupo> psqPorOperacao(String chave);


}
