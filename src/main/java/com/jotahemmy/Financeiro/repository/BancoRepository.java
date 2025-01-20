package com.jotahemmy.Financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotahemmy.Financeiro.model.entidades.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {

}
