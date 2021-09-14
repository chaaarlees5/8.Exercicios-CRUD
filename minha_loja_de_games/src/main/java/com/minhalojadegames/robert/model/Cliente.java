package com.minhalojadegames.robert.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cliente {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @NotBlank String nome;
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 6) String senha;

	@OneToMany(mappedBy = "comprador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "comprador" })
	private List<Produto> produtosComprados = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Produto> getProdutosComprados() {
		return produtosComprados;
	}

	public void setProdutosComprados(List<Produto> produtosComprados) {
		this.produtosComprados = produtosComprados;
	}

}
