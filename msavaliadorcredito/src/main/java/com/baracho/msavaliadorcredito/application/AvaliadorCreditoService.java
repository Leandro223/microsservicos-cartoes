package com.baracho.msavaliadorcredito.application;

import com.baracho.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.baracho.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import com.baracho.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import com.baracho.msavaliadorcredito.domain.model.*;
import com.baracho.msavaliadorcredito.infra.clients.CartoesResourceCliente;
import com.baracho.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.baracho.msavaliadorcredito.infra.mqueue.SolicitarEmissaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

      private final ClienteResourceClient clienteResourceClient;

      private final CartoesResourceCliente cartoesResourceCliente;

      private final SolicitarEmissaoPublisher solicitarEmissaoPublisher;
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

      public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {

            try {

                  ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
                  ResponseEntity<List<Cartao>> cartaoResponse = cartoesResourceCliente.getCartoesRendaAte(renda);

                  List<Cartao> cartoes = cartaoResponse.getBody();

                  var listaCartoesAprovados = cartoes.stream().map(cartao -> {

                        DadosCliente dadosCliente = dadosClienteResponse.getBody();

                        BigDecimal limeteBasico = cartao.getLimiteBasico();
                        BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                        var fator = idadeBD.divide(BigDecimal.valueOf(10));
                        BigDecimal limiteAprovado = fator.multiply(limeteBasico);

                        CartaoAprovado aprovado = new CartaoAprovado();
                        aprovado.setCartao(cartao.getNome());
                        aprovado.setBandeira(cartao.getBandeira());
                        aprovado.setLimiteAprovado(limiteAprovado);

                        return aprovado;

                  }).collect(Collectors.toList());

                  return new RetornoAvaliacaoCliente(listaCartoesAprovados);

            }catch (FeignException.FeignClientException e) {
                  int status = e.status();
                  if(HttpStatus.NOT_FOUND.value() == status) {
                        throw new DadosClienteNotFoundException();
                  }

                  throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
            }

      }

      public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {

            try {
                  solicitarEmissaoPublisher.solicitarEmissaoCartao(dados);
                  var protocolo = UUID.randomUUID().toString();
                  return new ProtocoloSolicitacaoCartao(protocolo);

            }catch (Exception e) {
                  throw new ErroSolicitacaoCartaoException(e.getMessage());
            }
      }
}
