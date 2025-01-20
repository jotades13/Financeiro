package com.jotahemmy.Financeiro.controller.entidades;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.dto.UsuarioDto;
import com.jotahemmy.Financeiro.model.entidades.Usuario;
import com.jotahemmy.Financeiro.repository.UsuarioRepository;
import com.jotahemmy.Financeiro.service.entidades.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository repository;
  @Autowired
  private UsuarioService service;
  @Autowired
  private ApplicationEventPublisher publisher;


  @GetMapping
  public List<UsuarioDto> listar() {
    
      return repository.findAll()
            .stream()
            .map( x -> new UsuarioDto(x))
            .collect(Collectors.toList());
  }

  @GetMapping("/{_nome}") 
  public List<UsuarioDto> psqNomeSenha(@PathVariable String _nome ){
     List<Usuario> usuario = repository.psqNomeSenha(_nome);
     return usuario.stream()
                   .map( x -> new UsuarioDto(x))
                   .collect(Collectors.toList());
    } 
  
  @PostMapping
  public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario, HttpServletResponse response) {
    Usuario usuarioSalvo = repository.save(usuario);
      publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));
      return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long codigo) {
      repository.deleteById(codigo);
  }

  @PutMapping("/{codigo}") 
  public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo , @RequestBody Usuario usuario) {
      Usuario usuarioSalvo = service.atualizar(codigo, usuario);
      return ResponseEntity.ok(usuarioSalvo);                                                    
  }

}
