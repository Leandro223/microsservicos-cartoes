package com.baracho.mscartao.application;

import com.baracho.mscartao.domain.Cartao;
import com.baracho.mscartao.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartaoService {

      private final CartaoRepository cartaoRepository;
      @Transactional
      public Cartao save(Cartao cartao) {
            return cartaoRepository.save(cartao);
      }
}
