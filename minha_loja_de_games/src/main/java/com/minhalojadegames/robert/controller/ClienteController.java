package com.minhalojadegames.robert.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhalojadegames.robert.model.Cliente;
import com.minhalojadegames.robert.model.util.ClienteLogin;
import com.minhalojadegames.robert.repository.ClienteRepository;
import com.minhalojadegames.robert.service.ClienteService;

@RestController
@RequestMapping("/user")
public class ClienteController {
	
	private @Autowired ClienteRepository repositorio;
	private @Autowired ClienteService servico;

	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> pegarTodos() {
		List<Cliente> listaObjeto = repositorio.findAll();

		if (listaObjeto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaObjeto);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Cliente> buscaPorId(@PathVariable(value = "id") Long idUsuario) {

		return repositorio.findById(idUsuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> buscaPorNome(@PathVariable(value = "nome") String nome) {
		List<Cliente> listaObjeto = repositorio.findAllByNomeContainingIgnoreCase(nome);
		if (listaObjeto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaObjeto);
		}
	}

	@PostMapping("/registro")
	public ResponseEntity<Object> registraCliente(@Valid @RequestBody Cliente novoCliente) {
		Optional<Object> objectOptional = servico.registraCliente(novoCliente);

		if (objectOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}

	@PutMapping("/autoriza")
	public ResponseEntity<ClienteLogin> autorizacao(@Valid @RequestBody Optional<ClienteLogin> user) {
		return servico.authorizacao(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualiza")
	public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente atualizaInfo) {
		Optional<Cliente> clienteAtualizado = repositorio.findById(atualizaInfo.getId()); 
		
		if(clienteAtualizado.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.ok(repositorio.save(clienteAtualizado.get()));
		}
	}

	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deletaPorId(@PathVariable(value = "id") Long idCliente) {
		Optional<Cliente> idUsuario = repositorio.findById(idCliente);

		if(idUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			repositorio.deleteById(idCliente);
			return ResponseEntity.status(200).build();
		}		
	}
}