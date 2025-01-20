package com.jotahemmy.Financeiro.service.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.dto.GrupoDto;
import com.jotahemmy.Financeiro.model.entidades.Grupo;
import com.jotahemmy.Financeiro.repository.GrupoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class GrupoService {
  
  @Autowired
  private GrupoRepository repository;

  @PersistenceContext
  private EntityManager manager;

  public Grupo atualizar(Long codigo, Grupo grupo) {
    Grupo grupoSalva = buscarGrupo(codigo);

        BeanUtils.copyProperties(grupo, grupoSalva, "codigo");
        return repository.save(grupoSalva);
    }

    public Grupo buscarGrupo(Long codigo) {
        Optional<Grupo> grupo = repository.findById(codigo);
        return grupo.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<GrupoDto> listaGrupos(String operacao){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Grupo> criteriaQuery = builder.createQuery(Grupo.class);
        Root<Grupo> root = criteriaQuery.from(Grupo.class);
                                                                                                                                                                                                                                                                                                                         
        var predicates = new ArrayList<Predicate>();
       
        predicates.add(builder.equal(root.get("operacao"),operacao.toUpperCase()));
       
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Grupo> psq = manager.createQuery(criteriaQuery);

        return psq.getResultList()
                  .stream()
                  .map( x -> new GrupoDto(x))
                 .collect(Collectors.toList());
        
    }    

}
