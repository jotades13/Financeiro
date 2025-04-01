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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.entidades.GrupoFinanceiro;
import com.jotahemmy.Financeiro.repository.GrupoFinanceiroRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/grupofinanceiro")
public class GrupoFinanceiroController {

    @Autowired
    private GrupoFinanceiroRepository repository;
    @Autowired
    private ApplicationEventPublisher publisher;


    @GetMapping()
    public List<GrupoFinanceiro> psqLista(@RequestParam String descricao){
        return repository.psqPorDescricao(descricao);
    }

    @GetMapping("/{codigo}")
    public GrupoFinanceiro buscarPeloCodigo(@PathVariable Long codigo) {
        return this.repository.findById(codigo).orElse(null);
    }

    @PostMapping
    public ResponseEntity<GrupoFinanceiro> criar(@RequestBody GrupoFinanceiro grupoFinanceiro, HttpServletResponse response) {
        GrupoFinanceiro grupoSalvo = repository.save(grupoFinanceiro);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        repository.deleteById(codigo);
    }


    
}
