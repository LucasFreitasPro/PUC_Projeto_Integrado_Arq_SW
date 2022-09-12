package br.com.agrow.web.rebanhoapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PartoUnidadeAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPartoUnidadeAnimal;

	@Column(nullable = false)
	private LocalDate dataParto;

	@Column(nullable = false)
	private LocalDateTime dataRegistro;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_unidade_animal", referencedColumnName = "id_unidade_animal")
	private UnidadeAnimal unidadeAnimal;

	public Long getIdPartoUnidadeAnimal() {
		return idPartoUnidadeAnimal;
	}

	public void setIdPartoUnidadeAnimal(Long idPartoUnidadeAnimal) {
		this.idPartoUnidadeAnimal = idPartoUnidadeAnimal;
	}

	public UnidadeAnimal getUnidadeAnimal() {
		return unidadeAnimal;
	}

	public void setUnidadeAnimal(UnidadeAnimal unidadeAnimal) {
		this.unidadeAnimal = unidadeAnimal;
	}

	public LocalDate getDataParto() {
		return dataParto;
	}

	public void setDataParto(LocalDate dataParto) {
		this.dataParto = dataParto;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
