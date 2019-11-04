package br.com.logap.windfarm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "parque_eolico")
public class ParqueEolico extends Entidade{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true, length = 45)
	private String nome;

	private int latitude;
	private int longitude;

	@Column(nullable = false)
	private float potencia_instalada;

	@ManyToOne(fetch = FetchType.LAZY)
	private ComplexoEolico complexo_eolico;

	@OneToMany(mappedBy = "parque_eolico", cascade = CascadeType.ALL)
	private List<Aerogerador> aerogeradores;


	public ParqueEolico() {
		this.complexo_eolico = new ComplexoEolico();
	}


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

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public float getPotencia_instalada() {
		return potencia_instalada;
	}

	public void setPotencia_instalada(float potencia_instalada) {
		this.potencia_instalada = potencia_instalada;
	}

	public ComplexoEolico getComplexo_eolico() {
		return complexo_eolico;
	}

	public void setComplexo_eolico(ComplexoEolico complexo_eolico) {
		this.complexo_eolico = complexo_eolico;
	}

	public List<Aerogerador> getAerogeradores() {
		return aerogeradores;
	}

	public void setAerogeradores(List<Aerogerador> aerogeradores) {
		this.aerogeradores = aerogeradores;
	}
}
