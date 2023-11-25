package com.baracho.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException extends Exception {
      public DadosClienteNotFoundException() {
            super("Dados do cliente não encontrado para o cpf informado.");
      }
}
