package com.baracho.mscartao.infra.repository;

import com.baracho.mscartao.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
