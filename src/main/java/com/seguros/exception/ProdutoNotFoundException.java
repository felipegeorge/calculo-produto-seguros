package com.seguros.exception;

public class ProdutoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNotFoundException(String str) {
		super("Não foi possivel encontrar o produto: ["+ str +"]");
	}

}