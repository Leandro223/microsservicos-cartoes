package com.baracho.msclientes.application;

import com.baracho.msclientes.domain.Cliente;
import com.baracho.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

      private final ClienteRepository clienteRepository;
      public Cliente save(Cliente cliente) {
            return clienteRepository.save(cliente);
      }

      public Optional<Cliente> getByCpf(String cpf) {
            return clienteRepository.findByCpf(cpf);
      }
}
