package org.transportes.vemTransportes.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 5, max = 255)
	private String nome;

	@NotBlank
	@Size(min = 5, max = 255)
	private String usuario;

	@NotBlank
	@Size(min = 3, max = 255)
	private String senha;

	//@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	//@JsonIgnoreProperties("user")
	//private List<Cartao> cartao;

	public User() {

	}

	public User(Long id, @NotBlank @Size(min = 5, max = 255) String nome,
			@NotBlank @Size(min = 5, max = 255) String usuario, @NotBlank @Size(min = 3, max = 255) String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//public List<Cartao> getCartao() {
	//	return cartao;
	//}

	//public void setCartao(List<Cartao> cartao) {
	//	this.cartao = cartao;
	//}

}
