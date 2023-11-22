package com.baracho.msclientes.application;

import com.baracho.msclientes.application.representation.ClienteSaveRequest;
import com.baracho.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesResource {


      private final ClienteService clienteService;

      @GetMapping
      public String status(){
            return "ok";
      }

      @PostMapping
      public ResponseEntity save (@RequestBody ClienteSaveRequest request){
            Cliente cliente = request.toModel();
            clienteService.save(cliente);
            URI headerLocation = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .query("cpf={cpf}")
                      .buildAndExpand(cliente.getCpf())
                      .toUri();

            return ResponseEntity.created(headerLocation).build();
      }
}
