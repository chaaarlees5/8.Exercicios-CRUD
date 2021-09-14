package com.minhalojadegames.robert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhalojadegames.robert.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findAllByNomeContainingIgnoreCase(String nome);
	Optional<Cliente> findByEmail(String email);
}
