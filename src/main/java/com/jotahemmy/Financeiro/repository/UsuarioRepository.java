package com.jotahemmy.Financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jotahemmy.Financeiro.model.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
  
  
  @Query("select u from Usuario u where u.nome = :nome")
  List<Usuario> psqNomeSenha(@Param("nome") String nome);
  /*
  
  @Query("select u from Usuario u where u.nome = :nome and u.senha = :senha")
  User psqByNameAndSenha( @Param("nome") String nome,
  @Param("senha") String senha);
  
  @Query("select u from Usuario u where u.firstname = :#{#chave.nome}")
  List<User> findUsersByCustomersFirstname(@Param("chave") Customer customer);
  */


  
}
