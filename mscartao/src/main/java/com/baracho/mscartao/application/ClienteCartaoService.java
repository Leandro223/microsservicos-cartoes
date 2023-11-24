package com.baracho.mscartao.application;

import com.baracho.mscartao.domain.ClienteCartao;
import com.baracho.mscartao.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

      private final ClienteCartaoRepository clienteCartaoRepository;
      public List<ClienteCartao> listCartoesByCpf(String cpf) {
            return clienteCartaoRepository.findByCpf(cpf);
      }
}
