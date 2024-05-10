package com.seguros.api.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seguros.api.dto.ProdutoDto;
import com.seguros.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Produtos", description = "Api para cálculo de Produto de Seguros")
@RestController
@RequestMapping("/produtos")
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProdutoService produtoService;
		
	@Operation(summary = "Listar todos os produtos")
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoDto> obterProdutos() {
		log.info("Obtendo uma lista de produtos de seguros...");
		return produtoService.obterProdutos();
	}
	
	@Operation(summary = "Consultar um produto através do ID")
	@GetMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ProdutoDto obterProduto(@PathVariable Long id) {
		log.info(String.format("Obter o produto com ID [%s]", id));
		return produtoService.obterProduto(id);
	}
	
	@Operation(summary = "Cadastrar um novo produto")
	@PostMapping(produces= {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto salvarNovoProduto(@Validated @RequestBody ProdutoDto dto) {
		log.info(String.format("Salvar o produto [%s]", dto));
		return produtoService.persistirProduto(dto);
	}
	
	@Operation(summary = "Atualizar um produto")
	@PutMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarProduto(@PathVariable Long id, @Validated @RequestBody ProdutoDto dto) {
		dto.setId(id);
		log.info(String.format("Atualizar o cadastro do produto [%s]", dto));
		produtoService.persistirProduto(dto);
	}
	
	@Operation(summary = "Remover um produto")
	@DeleteMapping(path = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerProduto(@PathVariable Long id) {
		log.info(String.format("Remover o produto com ID [%s]", id));
		produtoService.removerProduto(id);
	}
}
