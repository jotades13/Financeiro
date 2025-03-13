package com.jotahemmy.Financeiro.service.entidades;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jotahemmy.Financeiro.model.dto.LancamentoDto;
import com.jotahemmy.Financeiro.model.entidades.Lancamentos;
import com.jotahemmy.Financeiro.model.enums.Contas;
import com.jotahemmy.Financeiro.model.enums.Status;
import com.jotahemmy.Financeiro.repository.LancamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class LancamentoService {
  @Autowired
  private LancamentoRepository repository;
  @PersistenceContext
  private EntityManager manager;

  // alterando os dados de todo objeto (put)
  public Lancamentos atualizar(Long titulo, Lancamentos lancamento){
    Lancamentos lancamentoSalvo = buscarLancamento(titulo);
    BeanUtils.copyProperties(lancamento, lancamentoSalvo,"titulo");
    return repository.save(lancamentoSalvo);
  }  

  // Alterando os dados especificados do objeto (patch)
  public Lancamentos atualizaParcial(Long titulo, @RequestBody Map<String, Object> campos){
    // se caso o lancamento não exista retornará null
    Lancamentos lancamentoAtual = buscarLancamento(titulo);
    if (lancamentoAtual == null){return lancamentoAtual;}
    
    // O Objetivo é de mesclar os campos para o lancamentoAtual
    merge(campos,lancamentoAtual);
    
    return atualizar(titulo, lancamentoAtual);
  }
  
  public Lancamentos buscarLancamento(Long titulo) {
    Optional<Lancamentos> lancamento = repository.findById(titulo);
    return lancamento.orElseThrow(() -> new EmptyResultDataAccessException(1));
  }

  private void merge(Map<String, Object> dadosOrigem, Lancamentos lancamentoDestino){
    ObjectMapper objectMapper = new ObjectMapper();
    // Criando uma instancia lancamentoOrigem a partir dos dadosOrigem do tipo Lancamento 
    Lancamentos lancamentoOrigem = objectMapper.convertValue(dadosOrigem, Lancamentos.class);

    //atualizando os campos da class lancamentoDestino com os dadosOrigem
    dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
      //Cria uma instância de um campo de uma class
      Field field = ReflectionUtils.findField(Lancamentos.class,nomePropriedade);
      // acessando uma propriedade privada de um classe (uma propriedade privada só pode ser acessada na mesma class)
      if(field!=null){
        field.setAccessible(true);  //.setAccessible(true);
        Object novoValorPropriedade = ReflectionUtils.getField(field, lancamentoOrigem);
        ReflectionUtils.setField(field, lancamentoDestino, novoValorPropriedade);
      } 
    });
  }

  public void baixaTitulo(String databx,String usubx,BigDecimal valorbx,String bancobx,String formapg,Long titulo){
  
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate dataBaixa = LocalDate.parse(databx,formato);
    
    repository.baixaTitulo(dataBaixa,usubx,valorbx,bancobx,formapg,"BAIXADO",titulo);
  }

  public void desfazBaixa(Long titulo){
    repository.desfazBaixa("XXX","EMABERTO",titulo);
  }

  public List<LancamentoDto> listaLancamentos(Integer _ccusto, Contas _contas, Status _status, Integer _ano, Integer _mes, Integer _lancadosdias, String _descricao){

    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Lancamentos> criteriaQuery = builder.createQuery(Lancamentos.class);
    Root<Lancamentos> root = criteriaQuery.from(Lancamentos.class);
   
    var predicates = new ArrayList<Predicate>();
    
    
    if(_ccusto>0){
      predicates.add(builder.equal(root.get("centroCusto").get("codigo"), _ccusto));
    }
    predicates.add(builder.equal(root.get("contas"), _contas));
    predicates.add(builder.equal(root.get("status"), _status));
    
    
    if(_lancadosdias>0){
      LocalDate segundaData = LocalDate.now(); 
      LocalDate primeiraData  = segundaData.minusDays(_lancadosdias);  // Trata-se da data  menor
      predicates.add(builder.between(root.get("usuarioCadastroAlteracao").get("dataCadastro"),primeiraData, segundaData));
    }else{
      if(_ano>0){
        Expression<Integer> anoPesquisa = null;
        Expression<Integer> mesPesquisa = null;
        if (_status == Status.BAIXADO){
          anoPesquisa = builder.function("date_part", Integer.class,builder.literal("year"), root.get("dataBaixa"));
          mesPesquisa = builder.function("date_part", Integer.class,builder.literal("month"), root.get("dataBaixa"));
        }else{
          anoPesquisa = builder.function("date_part", Integer.class,builder.literal("year"), root.get("vencimento"));
          mesPesquisa = builder.function("date_part", Integer.class,builder.literal("month"), root.get("vencimento"));
        }
        predicates.add(builder.equal(anoPesquisa, _ano));
        predicates.add(builder.equal(mesPesquisa, _mes));
      }
    }
    
    if(_descricao != null && !_descricao.isEmpty()){
      predicates.add(builder.like(builder.upper(root.get("descricao")), "%"+_descricao.toUpperCase()+"%"));
    }

    criteriaQuery.where(predicates.toArray(new Predicate[0]));

    TypedQuery<Lancamentos> psq = manager.createQuery(criteriaQuery);

    return psq.getResultList()
                .stream()
                .map( x -> new LancamentoDto(x))
               .collect(Collectors.toList());
      
  }  
}
