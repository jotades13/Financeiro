package com.jotahemmy.Financeiro.controller.entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.entidades.LancamentoFinanceiro;
import com.jotahemmy.Financeiro.repository.LancamentoFinanceiroRepository;
import com.jotahemmy.Financeiro.service.entidades.LancamentoFinanceiroService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/lancamentofinanceiro")
public class LancamentoFinanceiroController {

    @Autowired
    private LancamentoFinanceiroRepository repository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired 
    private LancamentoFinanceiroService service;  
    
    @GetMapping
    public List<LancamentoFinanceiro> psqLista(@RequestParam String _ano, String _mes, Long _grupo, String _descricao){
        List<LancamentoFinanceiro> lancamentos = service.listaLancamentos(_ano,_mes,_grupo,_descricao);

        if(lancamentos==null){
            return null;
        }else{
            return lancamentos;
        }
    }

    @GetMapping("/{sequencia}")
    public ResponseEntity<LancamentoFinanceiro> psqLancamento(@PathVariable Long sequencia){

      LancamentoFinanceiro lancamento = service.buscar(sequencia);

      if(lancamento==null){
        return ResponseEntity.notFound().build();
      }else{
        return ResponseEntity.ok(lancamento);
      }
    }

    @PostMapping
    public ResponseEntity<LancamentoFinanceiro> criar(@RequestBody LancamentoFinanceiro lancamento, HttpServletResponse response) {
        LancamentoFinanceiro lancamentoSalvo = repository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getSequencia()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

  @PutMapping("/{sequencia}") 
  public ResponseEntity<LancamentoFinanceiro> atualizar(@PathVariable Long sequencia , @RequestBody LancamentoFinanceiro lancamento) {
      LancamentoFinanceiro lancamentoSalvo = service.atualizar(sequencia, lancamento);
    return ResponseEntity.ok(lancamentoSalvo);                                              
  }

  @DeleteMapping("/{sequencia}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long sequencia) {
        repository.deleteById(sequencia);
    }
}
