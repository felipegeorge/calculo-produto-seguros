package com.seguros.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seguros.data.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	@Query("SELECT p FROM ProdutoEntity p WHERE upper(p.categoria) like upper(:categoria)")
	List<ProdutoEntity> findByCategoria(String categoria);
	
}
