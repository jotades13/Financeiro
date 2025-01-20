package com.jotahemmy.Financeiro.service.entidades;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.entidades.Usuario;
import com.jotahemmy.Financeiro.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UsuarioService {
  
  @Autowired
  private UsuarioRepository repository;

  @PersistenceContext
  private EntityManager manager;

  public Usuario atualizar(Long codigo, Usuario usuario) {
      Usuario usuarioSalvo = buscarUsuario(codigo);

      BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
      return repository.save(usuarioSalvo);
  }

  public Usuario buscarUsuario(Long codigo) {
      Optional<Usuario> usuario = repository.findById(codigo);
      return usuario.orElseThrow(() -> new EmptyResultDataAccessException(1));
  }

}
