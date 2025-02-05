package com.jotahemmy.Financeiro.service.entidades;

import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.entidades.Log;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.ArrayList;

@Service
public class LogService {
   
    @PersistenceContext
    private EntityManager manager;

  public List<Log> listaLog(String _modulo, Number _id){
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Log> criteriaQuery = builder.createQuery(Log.class);
    Root<Log> root = criteriaQuery.from(Log.class);
                                                                                                                                                                                                                                                                                                                      
    var predicates = new ArrayList<Predicate>();
    
    predicates.add(builder.equal(root.get("modulo"),_modulo.trim().toUpperCase()));
    predicates.add(builder.equal(root.get("chave"),_id));
    
   // if(_id != null){
   //   predicates.add(builder.equal(root.get("chave"),"2")); 
   // }
   
    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    criteriaQuery.orderBy(builder.desc(root.get("sequencia")));
    TypedQuery<Log> psq = manager.createQuery(criteriaQuery);
    return psq.getResultList();
  }    


}
