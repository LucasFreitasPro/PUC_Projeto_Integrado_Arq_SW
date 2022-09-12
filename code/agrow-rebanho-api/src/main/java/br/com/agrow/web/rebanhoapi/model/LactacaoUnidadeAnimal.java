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

@Entity
public class LactacaoUnidadeAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLactacaoUnidadeAnimal;

	@Column(nullable = false)
	private LocalDate dataProducao;

	@Column(nullable = false)
	private Integer producao;

	@Column(nullable = false)
	private LocalDateTime dataRegistro;

	@ManyToOne
	@JoinColumn(name = "id_unidade_animal", referencedColumnName = "id_unidade_animal")
	private UnidadeAnimal unidadeAnimal;

	public Long getIdLactacaoUnidadeAnimal() {
		return idLactacaoUnidadeAnimal;
	}

	public void setIdLactacaoUnidadeAnimal(Long idLactacaoUnidadeAnimal) {
		this.idLactacaoUnidadeAnimal = idLactacaoUnidadeAnimal;
	}

	public LocalDate getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(LocalDate dataProducao) {
		this.dataProducao = dataProducao;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Integer getProducao() {
		return producao;
	}

	public void setProducao(Integer producao) {
		this.producao = producao;
	}

	public UnidadeAnimal getUnidadeAnimal() {
		return unidadeAnimal;
	}

	public void setUnidadeAnimal(UnidadeAnimal unidadeAnimal) {
		this.unidadeAnimal = unidadeAnimal;
	}
}
