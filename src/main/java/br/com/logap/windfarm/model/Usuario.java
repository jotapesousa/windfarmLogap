package br.com.logap.windfarm.model;

import javax.persistence.*;

import br.com.logap.windfarm.model.Entidade;

import java.io.Serializable;

@Entity
public class Usuario extends Entidade implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String login;
	private String senha;
	private String nome;

	public Usuario() {
		
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() { return login; }

	public void setLogin(String login) { this.login = login; }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
