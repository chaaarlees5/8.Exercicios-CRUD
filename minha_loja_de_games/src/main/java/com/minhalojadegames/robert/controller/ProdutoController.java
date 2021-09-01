package com.minhalojadegames.robert.controller;

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

import com.minhalojadegames.robert.model.Produto;
import com.minhalojadegames.robert.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private @Autowired ProdutoRepository repositorio;
	
	@GetMapping("/tudo")
	public ResponseEntity<List<Produto>> pegarTodos() {
		List<Produto> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/id/{id_produto}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(value = "id_idproduto") Long idProduto) {
		Optional<Produto> objetoId = repositorio.findById(idProduto);

		if (objetoId.isPresent()) {
			return ResponseEntity.status(200).body(objetoId.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> buscarPorTitulo(@PathVariable(value = "titulo") String titulo) {
		List<Produto> objetoDescricao = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoDescricao.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoDescricao);
		}
	}
	
	@PostMapping("/salvar/novo")
	public ResponseEntity<Produto> gravarNovoProduto(@Valid @RequestBody Produto novoProduto) {
		return ResponseEntity.status(201).body(repositorio.save(novoProduto));
	}
	
	@PutMapping("/atualizar/produto")
	public ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto atualizarProduto) {
		return ResponseEntity.status(201).body(repositorio.save(atualizarProduto));
	}
	
	@DeleteMapping("/deletar/{id_produto}")
	public void deletarTemaPorId(@PathVariable(value = "id_produto") Long idProduto) {
		repositorio.deleteById(idProduto);
	}
}
