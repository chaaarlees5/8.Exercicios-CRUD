package com.minhalojadegames.robert.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhalojadegames.robert.model.Cliente;
import com.minhalojadegames.robert.model.util.ClienteLogin;
import com.minhalojadegames.robert.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;

	public Optional<Object> registraCliente(Cliente usuario) {
		return repositorio.findByEmail(usuario.getEmail()).map(emailExists -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositorio.save(usuario));
		});
	}

	public Optional<ClienteLogin> authorizacao(Optional<ClienteLogin> usuario) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Cliente> emailUsuario = repositorio.findByEmail(usuario.get().getEmail());

		if (emailUsuario.isPresent()) {
			if (encoder.matches(usuario.get().getSenha(), emailUsuario.get().getSenha())) {

				String auth = usuario.get().getEmail() + ":" + usuario.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuario.get().setToken(authHeader);
				usuario.get().setNome(emailUsuario.get().getNome());

				return usuario;
			}
		}
		return Optional.empty();
	}
}
