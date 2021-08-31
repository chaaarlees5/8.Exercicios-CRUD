package com.minhalojadegames.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhalojadegames.robert.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	/**
	 * Método utilizado para pesquisar coluna tema ContainigIgnoreCase
	 * 
	 * @param nome do tipo String
	 * @return List de Temas
	 * @author charlô
	 */
	List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
}
