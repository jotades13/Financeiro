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
import com.jotahemmy.Financeiro.model.entidades.CentroCusto;
import com.jotahemmy.Financeiro.repository.CentroCustoRepository;
import com.jotahemmy.Financeiro.service.entidades.CentroCustoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/centrocusto")
public class CentroCustoController {
  
  @Autowired
  private CentroCustoRepository repository;
 
  @Autowired
  private CentroCustoService service;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public List<CentroCusto> listar(@RequestParam String chave){
    return this.service.listaCCusto(chave);
  } 

  @GetMapping("/{codigo}")
  public CentroCusto buscarPeloCodigo(@PathVariable Long codigo) {
    return this.repository.findById(codigo).orElse(null);
  }  

  @PostMapping
  public ResponseEntity<CentroCusto> criar(@RequestBody CentroCusto centroCusto, HttpServletResponse response) {
    CentroCusto centroCustoSalvo = repository.save(centroCusto);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, centroCustoSalvo.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(centroCustoSalvo);
  }
  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long codigo) {
      repository.deleteById(codigo);
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<CentroCusto> atualizar(@PathVariable Long codigo, @RequestBody CentroCusto centroCustoAtualizado) {
        
      if(!repository.existsById(codigo)){return ResponseEntity.notFound().build();}
      
      centroCustoAtualizado.setCodigo(codigo);
      CentroCusto centroCustoSalvo = repository.save(centroCustoAtualizado);
      return ResponseEntity.ok(centroCustoSalvo);
  }

}
