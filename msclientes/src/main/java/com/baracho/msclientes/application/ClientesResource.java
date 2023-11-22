package com.baracho.msclientes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesResource {

      @GetMapping
      public String status(){
            return "ok";
      }
}
