package com.jotahemmy.Financeiro.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController

public class jota {
  
@GetMapping("/")
public String getMethodName() {
    return "Olá Jotahemmy Silva";
}
}
