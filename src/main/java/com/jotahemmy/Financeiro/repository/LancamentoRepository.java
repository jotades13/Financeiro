package com.jotahemmy.Financeiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.jotahemmy.Financeiro.model.entidades.Lancamentos;

import jakarta.transaction.Transactional;

public interface LancamentoRepository extends JpaRepository<Lancamentos, Long>{

    @Transactional
    @Modifying
    @Query(value = "update tb_lancamentos set lan_bxdata=null,lan_bxusu=null,"+
                   " lan_bxvalor=0,lan_bxbanco=null,lan_bxforma=?1,lan_status=?2 "+
                   " where lan_titulo=?3", nativeQuery = true)
    void desfazBaixa(String formapg,String status,Long titulo);

    @Transactional
    @Modifying
    @Query(value = "update tb_lancamentos set lan_bxdata=?1,lan_bxusu=?2,"+
                   " lan_bxvalor=?3,lan_bxbanco=?4,lan_bxforma=?5,lan_status=?6"+
                   " where lan_titulo=?7", nativeQuery = true)
    void baixaTitulo(LocalDate databx, String usubx, BigDecimal valorbx ,String bancobx,String formapg,String status,Long titulo);

    // Troquei essas pesquisas pelo critéria - Nos dá bem mais condicoes de trabalo
    // Pesquisando lancamentos
    //--
        
//    @Query("select l from Lancamentos l    "+
//           " where l.centroCusto.codigo = :ccusto "+ 
//           "   and l.contas = :contas      "+
//           "   and l.status = :status      "+
//           "   and extract(year  from l.vencimento) = :ano "+
//           "   and extract(month from l.vencimento) = :mes ")
 //   List<LancamentoDto> psqLancamentosEmAberto(
//      @Param("ccusto") Long ccusto,
//      @Param("contas") Contas contas,
//     @Param("status") Status status,
//      @Param("ano") Long ano,
//      @Param("mes") Long mes);

//    @Query("select l from Lancamentos l  "+
//           " where l.centroCusto.codigo=:ccusto "+
//           "   and l.contas = :contas    "+
//           "   and l.status = :status    "+
//           "   and extract(year  from l.dataBaixa) = :ano "+
//           "   and extract(month from l.dataBaixa) = :mes ")
//    List<LancamentoDto> psqLancamentosBaixado(
//    @Param("ccusto") Long ccusto,  
//    @Param("contas") Contas contas,
//    @Param("status") Status status,
//    @Param("ano") Long ano,
//    @Param("mes") Long mes);

}

