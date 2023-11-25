package com.baracho.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception {

      @Getter
      private Integer status;
      public ErroComunicacaoMicroservicesException(String msg, int status) {
            super(msg);
            this.status = status;
      }
}
