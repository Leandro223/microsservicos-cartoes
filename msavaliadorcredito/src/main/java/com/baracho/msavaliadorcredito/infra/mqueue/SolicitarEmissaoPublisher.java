package com.baracho.msavaliadorcredito.infra.mqueue;

import com.baracho.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitarEmissaoPublisher {


      private final RabbitTemplate rabbitTemplate;
      private final Queue queueEmissaoCartoes;


      public void solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
            var json = convertIntoJson(dados);
            rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);

      }

      private String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {

            ObjectMapper mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(dados);
            return json;
      }
}
