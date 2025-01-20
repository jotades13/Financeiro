package com.jotahemmy.Financeiro.controller.entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.entidades.Grupo;
import com.jotahemmy.Financeiro.repository.GrupoRepository;
import com.jotahemmy.Financeiro.service.entidades.GrupoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

  @Autowired
  private GrupoRepository repository;
  @Autowired
  private GrupoService service;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping()
  public List<Grupo> pslistar(@RequestParam String operacao) {
      return repository.psqPorOperacao(operacao);
  }

  @GetMapping("/{codigo}")
  public Grupo buscarPeloCodigo(@PathVariable Long codigo) {
      return this.repository.findById(codigo).orElse(null);
  }

  @PostMapping
  public ResponseEntity<Grupo> criar(@RequestBody Grupo grupo, HttpServletResponse response) {
    Grupo grupoSalvo = repository.save(grupo);
      publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalvo.getCodigo()));
      return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long codigo) {
      repository.deleteById(codigo);
  }

  @PutMapping("/{codigo}") 
  public ResponseEntity<Grupo> atualizar(@PathVariable Long codigo , @RequestBody Grupo grupo) {
      Grupo grupoSalvo = service.atualizar(codigo, grupo);
      return ResponseEntity.ok(grupoSalvo);                                                    
  }
}
