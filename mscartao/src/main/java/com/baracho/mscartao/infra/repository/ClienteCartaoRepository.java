package com.baracho.mscartao.infra.repository;

import com.baracho.mscartao.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
      List<ClienteCartao> findByCpf(String cpf);
}
