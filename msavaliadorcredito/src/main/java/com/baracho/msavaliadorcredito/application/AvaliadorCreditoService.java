package com.baracho.msavaliadorcredito.application;

import com.baracho.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.baracho.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import com.baracho.msavaliadorcredito.domain.model.CartoesCliente;
import com.baracho.msavaliadorcredito.domain.model.DadosCliente;
import com.baracho.msavaliadorcredito.domain.model.SituacaoCliente;
import com.baracho.msavaliadorcredito.infra.clients.CartoesResourceCliente;
import com.baracho.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

      private final ClienteResourceClient clienteResourceClient;

      private final CartoesResourceCliente cartoesResourceCliente;
      public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {

            try {
                  ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
                  ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesResourceCliente.getCartoesByCliente(cpf);

                  return SituacaoCliente
                            .builder()
                            .cliente(dadosClienteResponse.getBody())
                            .cartoes(cartoesResponse.getBody())
                            .build();

            }catch (FeignException.FeignClientException e) {
                  int status = e.status();
                  if(HttpStatus.NOT_FOUND.value() == status) {
                        throw new DadosClienteNotFoundException();
                  }

                  throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);

            }
      }
}
