package com.baracho.msclientes.infra.repository;

import com.baracho.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
