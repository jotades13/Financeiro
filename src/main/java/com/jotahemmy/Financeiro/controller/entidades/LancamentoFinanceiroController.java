package com.jotahemmy.Financeiro.controller.entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jotahemmy.Financeiro.model.entidades.LancamentoFinanceiro;
import com.jotahemmy.Financeiro.repository.GrupoFinanceiroRepository;
import com.jotahemmy.Financeiro.service.entidades.LancamentoFinanceiroService;

@RestController
@RequestMapping("/lancamentofinanceiro")
public class LancamentoFinanceiroController {

 
    @Autowired
    private GrupoFinanceiroRepository repository;
    @Autowired
    private ApplicationEventPublisher publisher;
     
    private LancamentoFinanceiroService service;  
    
    @GetMapping()
    public List<LancamentoFinanceiro> psqLista(@RequestParam Integer ano, Integer mes, String descricao){
        List<LancamentoFinanceiro> lancamentos = this.service.listaLancamentos(ano,mes,descricao);

        if(lancamentos==null){
            return null;
        }else{
            return lancamentos;
        }
    }
}
