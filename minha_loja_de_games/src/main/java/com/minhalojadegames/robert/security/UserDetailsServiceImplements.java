package com.minhalojadegames.robert.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.minhalojadegames.robert.model.Cliente;
import com.minhalojadegames.robert.repository.ClienteRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private ClienteRepository repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Cliente> objetoOptional = repositorio.findByEmail(username);

		if (objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());
		} else {
			throw new UsernameNotFoundException(username + "não existe!");
		}
	}
}
