package com.baracho.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RetornoAvaliacaoCliente {

      List<CartaoAprovado> cartoes;
}
