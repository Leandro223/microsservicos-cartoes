package com.baracho.msclientes.application;

import com.baracho.msclientes.domain.Cliente;
import com.baracho.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

      private final ClienteRepository clienteRepository;
      public Cliente save(Cliente cliente) {
            return clienteRepository.save(cliente);
      }
}
