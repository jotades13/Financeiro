package com.jotahemmy.Financeiro.controller.entidades;
 
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jotahemmy.Financeiro.event.RecursoCriadoEvent;
import com.jotahemmy.Financeiro.model.dto.BancoDto;
import com.jotahemmy.Financeiro.model.entidades.Banco;
import com.jotahemmy.Financeiro.repository.BancoRepository;
import com.jotahemmy.Financeiro.service.entidades.BancoService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


//@CrossOrigin("*")
@RestController
@RequestMapping("/bancos")
public class BancoControle {

    @Autowired
    private BancoRepository repository;
    @Autowired
    private BancoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Banco> listar() {
        return repository.findAll();
    }

    @GetMapping("/{codigo}")
    public Banco buscarPeloCodigo(@PathVariable Long codigo) {
        return this.repository.findById(codigo).orElse(null);
    }

    @GetMapping("/chave")
    public List<BancoDto> buscarPeloCodigo(@RequestParam String chave) {
        return this.service.listaBancos(chave);
    }

    @PostMapping
    public ResponseEntity<Banco> criar(@RequestBody Banco banco, HttpServletResponse response) {
        Banco bancoSalva = repository.save(banco);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, bancoSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoSalva);
    }
 
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        System.out.println("jotahemmy apagando banco");
        System.out.println(codigo);
        repository.deleteById(codigo);
    }

  //  @PutMapping("/{codigo}") 
  //  public ResponseEntity<Banco> atualizar(@PathVariable Long codigo , @RequestBody Banco banco) {
  //      Banco bancoSalva = service.atualizar(codigo, banco);
  //      return ResponseEntity.ok(bancoSalva);                                                    
  //  }

    @PutMapping("/{codigo}")
    public ResponseEntity<Banco> atualizar2(@PathVariable Long codigo, @RequestBody Banco bancoAtualizado) {
        
        if(!repository.existsById(codigo)){return ResponseEntity.notFound().build();}
        
        bancoAtualizado.setCodigo(codigo);
        Banco bancoSalvo = repository.save(bancoAtualizado);
        return ResponseEntity.ok(bancoSalvo);
    }

///	@PutMapping("/{pCodigo}")
//	@Transactional
//	public ResponseEntity<Banco> atualizar(@PathVariable String pCodigo,
//			@RequestBody Banco bancoAtualizado) {
//		if (!bancoRepository.existsById(pCodigo)) {
//			return ResponseEntity.notFound().build();
//		}
//		bancoAtualizado.setBanco(pCodigo);
//		Banco bancoSalvo = bancoRepository.save(bancoAtualizado);
//		return ResponseEntity.ok(bancoSalvo);
//	}

}
