package com.seguros.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@NonNull
	@JsonProperty("nome")
	private String nome;
	
	@NonNull
	@JsonProperty("categoria")
	private String categoria;
	
	@NonNull
	@JsonProperty("preco_base")
	private Double precoBase;
	
	@JsonProperty("preco_tarifado")
	private Double precoTarifado;

}
