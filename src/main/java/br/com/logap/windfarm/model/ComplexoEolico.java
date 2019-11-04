package br.com.logap.windfarm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "complexo_eolico")
public class ComplexoEolico extends Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true, length = 45)
	private String nome;

	@Column(length = 45)
	private String uf;

	@Column(length = 45)
	private String identificador;

	@OneToMany(mappedBy = "complexo_eolico", cascade = CascadeType.ALL)
	private List<ParqueEolico> parques;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public List<ParqueEolico> getParques() {
		return parques;
	}

	public void setParques(List<ParqueEolico> parques) {
		this.parques = parques;
	}
}