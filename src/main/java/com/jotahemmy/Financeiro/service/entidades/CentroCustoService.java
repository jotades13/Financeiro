package com.jotahemmy.Financeiro.service.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.entidades.CentroCusto;
import com.jotahemmy.Financeiro.repository.CentroCustoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CentroCustoService {
   
  @Autowired
  private CentroCustoRepository repository;

  @PersistenceContext
  private EntityManager manager;

  public CentroCusto atualizar(Long _codigo, CentroCusto _ccusto){
        CentroCusto centrocustoSalvo = buscarCentroCusto(_codigo);

        BeanUtils.copyProperties(_ccusto, centrocustoSalvo, "codigo");
        return repository.save(centrocustoSalvo);
    }

    public CentroCusto buscarCentroCusto(Long _codigo){
        Optional<CentroCusto> centrocusto = repository.findById(_codigo);
        return centrocusto.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void apagaCentroCustro(Long _codigo){

    }

    public List<CentroCusto> listaCCusto(String _chave){
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<CentroCusto> criteriaQuery = builder.createQuery(CentroCusto.class);
    Root<CentroCusto> root = criteriaQuery.from(CentroCusto.class);
                                                                                                                                                                                                                                                                                                                        
    var predicates = new ArrayList<Predicate>();
    
    predicates.add(builder.like(root.get("descricao"), "%"+_chave
        .trim()
        .toUpperCase()+"%"));
    
    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    TypedQuery<CentroCusto> psq = manager.createQuery(criteriaQuery);

    return psq.getResultList();
   //             .stream()
     //           .collect(Collectors.toList());
    }   

 }
