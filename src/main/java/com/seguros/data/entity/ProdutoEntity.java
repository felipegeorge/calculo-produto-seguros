package com.seguros.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class ProdutoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "HIBERNATE_SEQUENCE_GENERATOR", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="preco_base")
	private Double precoBase;
	
	@Column(name="preco_tarifado")
	private Double precoTarifado;
	
}
