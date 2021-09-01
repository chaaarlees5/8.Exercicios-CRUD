package com.farmacia.farmacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacao.model.Categoria;
import com.farmacia.farmacao.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private @Autowired CategoriaRepository repositorio;

	@GetMapping("/tudo")
	public ResponseEntity<List<Categoria>> pegarTodos() {
		List<Categoria> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/id/{id_categoria}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable(value = "id_categoria") Long idCategoria) {
		Optional<Categoria> objetoId = repositorio.findById(idCategoria);

		if (objetoId.isPresent()) {
			return ResponseEntity.status(200).body(objetoId.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> buscarPorCategoriaI(@PathVariable(value = "descricao") String descricao) {
		List<Categoria> objetoDescricao = repositorio.findAllByDescricaoContainingIgnoreCase(descricao);

		if (objetoDescricao.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoDescricao);
		}
	}
	
	@PostMapping("/salvar/nova")
	public ResponseEntity<Categoria> gravarNovaCategoria(@Valid @RequestBody Categoria novaCategoria) {
		return ResponseEntity.status(201).body(repositorio.save(novaCategoria));
	}
	
	@PutMapping("/atualizar/descricao")
	public ResponseEntity<Categoria> atualizar(@Valid @RequestBody Categoria atualizarCategoria) {
		return ResponseEntity.status(201).body(repositorio.save(atualizarCategoria));
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public void deletarTemaPorId(@PathVariable(value = "id_categoria") Long idCategoria) {
		repositorio.deleteById(idCategoria);
	}
}