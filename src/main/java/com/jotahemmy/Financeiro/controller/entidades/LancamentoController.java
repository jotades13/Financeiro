package com.jotahemmy.Financeiro.controller.entidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.dto.LancamentoDto;
import com.jotahemmy.Financeiro.model.entidades.Lancamentos;
import com.jotahemmy.Financeiro.model.enums.Contas;
import com.jotahemmy.Financeiro.model.enums.Status;
import com.jotahemmy.Financeiro.repository.LancamentoRepository;
import com.jotahemmy.Financeiro.service.entidades.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
  
  @Autowired
  private LancamentoRepository repository;
  @Autowired
  private LancamentoService service;
  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public List<LancamentoDto> listarDto(@RequestParam Integer ccusto, Contas contas, Status status, Integer ano, Integer mes, Integer lancadoshadias, String descricao,Boolean dspFixa, Integer grupo){ 
    List<LancamentoDto> lancamentos = service.listaLancamentos(ccusto, contas, status, ano, mes, lancadoshadias, descricao, dspFixa, grupo);
    if(lancamentos==null){
      return null;
    }else{
      return lancamentos;                                                    
   }
  }

  @GetMapping("/{titulo}")
  public ResponseEntity<Lancamentos> listar(@PathVariable Long titulo) {
    Lancamentos lancamento = service.buscarLancamento(titulo); 

    if(lancamento==null){
      return ResponseEntity.notFound().build();
    }else{
      return ResponseEntity.ok(lancamento);                                                    
   }
  }
  
  @PostMapping
    public ResponseEntity<Lancamentos> criar(@RequestBody Lancamentos lancamento, HttpServletResponse response) {
        Lancamentos lancamentoSalvo = repository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getTitulo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

  @DeleteMapping("/{titulo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long titulo) {
        repository.deleteById(titulo);
    }

  @PutMapping("/{titulo}") 
  public ResponseEntity<Lancamentos> atualizar(@PathVariable Long titulo , @RequestBody Lancamentos lancamento) {
      Lancamentos lancamentoSalvo = service.atualizar(titulo, lancamento);
    return ResponseEntity.ok(lancamentoSalvo);                                              
  }

  @PutMapping("/desfazbaixa")
  public ResponseEntity<?> desfazBaixa(@RequestParam Long titulo){
    
    service.desfazBaixa(titulo);
    
  return  ResponseEntity.ok().build();
  } 

  @PutMapping("/baixatitulo")
  public ResponseEntity<?> baixaTitulo(@RequestParam String databx, String usubx, BigDecimal valorbx, String bancobx,String formapg, Long titulo){

    //camentos set lan_bxdata=?1,lan_bxusu=?2,"+
    //" lan_bxvalor=?3,lan_bxbanco=?4,lan_bxforma=?5,lan_status=?6"+

    service.baixaTitulo(databx,usubx,valorbx,bancobx,formapg,titulo);

  return ResponseEntity.ok().build();
  }

  @PatchMapping("/{titulo}")                                          // map convertera os campos passados para um obj
  public ResponseEntity<Lancamentos> atualizaParcial(@PathVariable Long titulo, @RequestBody Map<String, Object> campos){

    Lancamentos lancamentoSalvo = service.atualizaParcial(titulo, campos);
    if (lancamentoSalvo == null){return ResponseEntity.notFound().build();}

  return ResponseEntity.ok(lancamentoSalvo);
  } 

  
}
