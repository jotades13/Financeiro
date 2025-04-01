package com.jotahemmy.Financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotahemmy.Financeiro.model.entidades.LancamentoFinanceiro;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long>{
    
}
