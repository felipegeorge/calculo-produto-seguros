package com.seguros.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguros.api.dto.ProdutoDto;
import com.seguros.data.entity.CategoriaTarifariaEntity;
import com.seguros.data.entity.ProdutoEntity;
import com.seguros.data.repository.CategoriaTarifariaRepository;
import com.seguros.data.repository.ProdutoRepository;
import com.seguros.exception.ProdutoNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ModelMapper modelMapper = new ModelMapper(); 
	
	@Autowired
	private CategoriaTarifariaRepository categoriaTarifariaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDto> obterProdutos() {
		log.info("Consultando na base de dados todos os produtos de seguros...");
		List<ProdutoEntity> list = produtoRepository.findAll();
		
		return list.stream()
				.map(entity -> modelMapper.map(entity, ProdutoDto.class))
				.collect(Collectors.toList());
	}
	
	public ProdutoDto obterProduto(Long id) {
		log.info(String.format("Consultando na base de dados o produto com id [%s]", id));
		return produtoRepository.findById(id)
				.map(entity -> modelMapper.map(entity, ProdutoDto.class))
				.orElseThrow(() -> new ProdutoNotFoundException(id.toString()));
	}
		
	public ProdutoDto persistirProduto(ProdutoDto dto) {
		log.info(String.format("Consultando os impostos incidentes para o produto com categoria [%s]", dto.getCategoria()));
		CategoriaTarifariaEntity categoria = categoriaTarifariaRepository.findByCategoria(dto.getCategoria());
		ProdutoEntity entity = modelMapper.map(dto, ProdutoEntity.class);
		entity.setPrecoTarifado(calcularPrecoTarifado(dto, categoria));
		
		ProdutoEntity entitySaved = produtoRepository.save(entity);
		log.info(String.format("Retornando o produto cadastrado/atualizado [%s]", entitySaved));
		
		return modelMapper.map(entitySaved, ProdutoDto.class);
	}
	
	public void removerProduto(Long id) {
		log.info(String.format("Removendo da base de dados o produto com id [%s]", id));
		produtoRepository.findById(id)
		.map(entity -> {
				produtoRepository.delete(entity);
				return Void.TYPE;
				})
		.orElseThrow(() -> new ProdutoNotFoundException(id.toString()));
	}
		
	private double calcularPrecoTarifado(ProdutoDto dto, CategoriaTarifariaEntity categoria){
		double precoBase = dto.getPrecoBase().doubleValue();
		double indiceIof = categoria.getIof().doubleValue()/100;
		double indicePis = categoria.getPis().doubleValue()/100;
		double indiceCofins = categoria.getCofins().doubleValue()/100;
		
		double precoTarifado = precoBase + (precoBase * indiceIof) + (precoBase * indicePis) + (precoBase * indiceCofins);
		
		return precoTarifado;
	}
	
}
