package com.jotahemmy.Financeiro.service.entidades;

import java.util.Optional;
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


    //public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    //repository
    //    @Query("SELECT l FROM Lancamento l WHERE l.contas IN :contas AND l.status IN :status")
    //    List<LancamentoDto> psqLancamentos(@Param("contas") Collection<Contas> contas, 
    //                                       @Param("status") Collection<Status> status);
    //}
    
    //service
    //public List<LancamentoDto> buscaLancamentos(List<String> contas, List<String> status) 
    // {  //
      // Converte as listas de strings para Collection<Contas> e Collection<Status> 
      // Collection<Contas> contasEnum = contas.stream() .map(Contas::valueOf) .collect(Collectors.toList());
      // Collection<Status> statusEnum = status.stream() .map(Status::valueOf) .collect(Collectors.toList()); 
      // return repository.psqLancamentos(contasEnum, statusEnum);
      // }
      
      //         Collection<DespesaFixa> despesaEnum = despesafixa.stream()
      //                                               .map(DespesaFixa::valueOf)
      //                                               .collect(Collectors.toList());
      public List<LancamentoDto> buscaLancamentos(Long ccusto, Contas contas, Status status, Long ano, Long mes){
     
        List<LancamentoDto> lancamentos;

        switch (status) {
          case AVENCER:
          lancamentos = repository.psqLancamentosAvencer(ccusto, contas, status, ano, mes);  
            break;
          case BAIXADO:
            lancamentos = repository.psqLancamentosBaixado(ccusto, contas, status, ano, mes);  
              break;       
          default:
            lancamentos = repository.psqLancamentosLancados(ccusto, contas, ano, mes);
            break;
        }
            
        return lancamentos.isEmpty() ? null : lancamentos;
        
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
      field.setAccessible(true);
      Object novoValorPropriedade = ReflectionUtils.getField(field, lancamentoOrigem);

      ReflectionUtils.setField(field, lancamentoDestino, novoValorPropriedade);
    });
  }

  public void baixaTitulo(String databx,String usubx,BigDecimal valorbx,String bancobx,String formapg,Long titulo){
  
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate dataBaixa = LocalDate.parse(databx,formato);
    
    repository.baixaTitulo(dataBaixa,usubx,valorbx,bancobx,formapg,"BAIXADO",titulo);
  }

  public void desfazBaixa(Long titulo){
    repository.desfazBaixa("XXX","EM_ABERTO",titulo);
  }

  public List<Lancamentos> listaLancamentos(Long _ano,Long _mes){

    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Lancamentos> criteriaQuery = builder.createQuery(Lancamentos.class);
    Root<Lancamentos> root = criteriaQuery.from(Lancamentos.class);
                                                                                                                                                                                                                                                                                                                 
    var predicates = new ArrayList<Predicate>();
  // Essa rotina não está funcionanco para captura do ano
    if (_ano != null){
      predicates.add(builder.equal( builder.function("year", Integer.class, root.get("vencimento")), _ano));
    }
  // Essa rotina não está funcionanco para captura do mês
    if (_mes != null){
      predicates.add(builder.equal( builder.function("month", Integer.class, root.get("vencimento")), _mes));
    } 
    
  TypedQuery<Lancamentos> psq = manager.createQuery(criteriaQuery);

// caso o retorno fosse igual a LancamentoDto
//  return psq.getResultList()
//            .stream()
//            .map( x -> new LancamentoDto(x))
//           .collect(Collectors.toList());

    return psq.getResultList();
  
  }  


}
