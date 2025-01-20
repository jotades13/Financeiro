package com.jotahemmy.Financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotahemmy.Financeiro.model.entidades.Log;

public interface LogRepository extends JpaRepository<Log,Long> {
  
}
