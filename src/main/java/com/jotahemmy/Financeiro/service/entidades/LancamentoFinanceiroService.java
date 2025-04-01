package com.jotahemmy.Financeiro.service.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.entidades.LancamentoFinanceiro;
import com.jotahemmy.Financeiro.repository.LancamentoFinanceiroRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class LancamentoFinanceiroService {

   @Autowired
   private LancamentoFinanceiroRepository repository; 

   @PersistenceContext
   private EntityManager manager;

  public LancamentoFinanceiro atualizar(Long _codigo, LancamentoFinanceiro _lancamento){
    LancamentoFinanceiro lancamentoSalvo = buscar(_codigo);

        BeanUtils.copyProperties(_lancamento, lancamentoSalvo, "codigo");
        return repository.save(lancamentoSalvo);
    }

    public LancamentoFinanceiro buscar(Long _codigo){
        Optional<LancamentoFinanceiro> lancamentoFinanceiro = repository.findById(_codigo);
        return lancamentoFinanceiro.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }


    public List<LancamentoFinanceiro> listaLancamentos(Integer _ano, Integer _mes, String _descricao){

      CriteriaBuilder builder = manager.getCriteriaBuilder();
      CriteriaQuery<LancamentoFinanceiro> criteriaQuery = builder.createQuery(LancamentoFinanceiro.class);
      Root<LancamentoFinanceiro> root = criteriaQuery.from(LancamentoFinanceiro.class);
    
      var predicates = new ArrayList<Predicate>();
          
      Expression<Integer> anoPesquisa = null;
      Expression<Integer> mesPesquisa = null;
      
      anoPesquisa = builder.function("date_part", Integer.class,builder.literal("year"), root.get("lancamento"));
      mesPesquisa = builder.function("date_part", Integer.class,builder.literal("month"), root.get("lancamento"));
      predicates.add(builder.equal(anoPesquisa, _ano));
      predicates.add(builder.equal(mesPesquisa, _mes));

      if(_descricao != null && !_descricao.isEmpty()){
        predicates.add(builder.like(builder.upper(root.get("descricao")), "%"+_descricao.toUpperCase()+"%"));
      }

      criteriaQuery.where(predicates.toArray(new Predicate[0]));
      TypedQuery<LancamentoFinanceiro> psq = manager.createQuery(criteriaQuery);

      return psq.getResultList();      
    }  


}
