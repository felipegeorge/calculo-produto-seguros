package com.seguros.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguros.data.entity.CategoriaTarifariaEntity;

@Repository
public interface CategoriaTarifariaRepository extends JpaRepository<CategoriaTarifariaEntity, Long> {

	CategoriaTarifariaEntity findByCategoria(String categoria);
	
}
