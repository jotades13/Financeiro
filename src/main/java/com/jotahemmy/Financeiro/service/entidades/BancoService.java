package com.jotahemmy.Financeiro.service.entidades;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jotahemmy.Financeiro.model.dto.BancoDto;
import com.jotahemmy.Financeiro.model.entidades.Banco;
import com.jotahemmy.Financeiro.repository.BancoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @PersistenceContext
    private EntityManager manager;

    public Banco atualizar(Long codigo, Banco banco) {
        Banco bancoSalva = buscarBanco(codigo);

        BeanUtils.copyProperties(banco, bancoSalva, "codigo");
        return bancoRepository.save(bancoSalva);
    }

    public Banco buscarBanco(Long codigo) {
        Optional<Banco> banco = bancoRepository.findById(codigo);
        return banco.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void apagaBanco(){
        
    } 

    public List<BancoDto> listaBancos(String _chave){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Banco> criteriaQuery = builder.createQuery(Banco.class);
        Root<Banco> root = criteriaQuery.from(Banco.class);
                                                                                                                                                                                                                                                                                                                          
       var predicates = new ArrayList<Predicate>();
       
               // VERIFICA SE USUARIO DIGITOU APENAS NÚMERO(BANCO) OU STRING(NOME)
        try {
            Long.parseLong (_chave); 
            predicates.add(builder.like(root.get("banco"), "%"+_chave
            .trim().
            toUpperCase()+"%"));
        } catch (NumberFormatException ex) {
            predicates.add(builder.like(root.get("nome"), "%"+_chave
            .trim()
            .toUpperCase()+"%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Banco> psq = manager.createQuery(criteriaQuery);

        return psq.getResultList()
                  .stream()
                  .map( x -> new BancoDto(x))
                 .collect(Collectors.toList());
        
    }    

}
