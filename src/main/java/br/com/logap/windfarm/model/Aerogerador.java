package br.com.logap.windfarm.model;

import javax.persistence.*;

@Entity
public class Aerogerador extends Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true, length = 45)
	private String nome;
	private float latitude;
	private float longitude;
	private float altura_torre;
	private float diametro_varredura;

	@Column(nullable = false, length = 45)
	private String modelo;

	@ManyToOne(fetch = FetchType.LAZY)
	private ParqueEolico parque_eolico;


	public Aerogerador() {
		this.parque_eolico = new ParqueEolico();
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getAltura_torre() {
		return altura_torre;
	}

	public void setAltura_torre(float altura_torre) {
		this.altura_torre = altura_torre;
	}

	public float getDiametro_varredura() {
		return diametro_varredura;
	}

	public void setDiametro_varredura(float diametro_varredura) {
		this.diametro_varredura = diametro_varredura;
	}

	public ParqueEolico getParque_eolico() {
		return parque_eolico;
	}

	public void setParque_eolico(ParqueEolico parque_eolico) {
		this.parque_eolico = parque_eolico;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
