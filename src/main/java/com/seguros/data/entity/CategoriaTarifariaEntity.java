package com.seguros.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria_tarifaria")
public class CategoriaTarifariaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="iof")
	private Double iof;
	
	@Column(name="pis")
	private Double pis;
	
	@Column(name="cofins")
	private Double cofins;

}
