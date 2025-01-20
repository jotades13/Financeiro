package com.jotahemmy.Financeiro.controller.entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jotahemmy.Financeiro.model.entidades.Log;
import com.jotahemmy.Financeiro.service.entidades.LogService;

@RestController
@RequestMapping("/log")  
public class LogControle {
  
  @Autowired
  private LogService service;

  @GetMapping
  public List<Log> buscarPeloModulo(@RequestParam String modulo, @RequestParam Number id){
    return this.service.listaLog(modulo,id);
  }
}
