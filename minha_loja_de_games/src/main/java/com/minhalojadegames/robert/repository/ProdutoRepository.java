package com.minhalojadegames.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhalojadegames.robert.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
}
