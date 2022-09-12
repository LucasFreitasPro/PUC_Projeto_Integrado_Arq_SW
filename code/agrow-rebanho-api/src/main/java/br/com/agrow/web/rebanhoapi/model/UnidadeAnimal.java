package br.com.agrow.web.rebanhoapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class UnidadeAnimal {

	@Id
	@Column(name = "id_unidade_animal")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUnidadeAnimal;

	@Column(nullable = false)
	private String identificacao;

	@OneToMany(mappedBy = "unidadeAnimal")
	private List<LactacaoUnidadeAnimal> lactacoes;

	@OneToMany(mappedBy = "unidadeAnimal")
	private List<PartoUnidadeAnimal> partos;

	public Long getIdUnidadeAnimal() {
		return idUnidadeAnimal;
	}

	public void setIdUnidadeAnimal(Long idUnidadeAnimal) {
		this.idUnidadeAnimal = idUnidadeAnimal;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public List<LactacaoUnidadeAnimal> getLactacoes() {
		return lactacoes;
	}

	public void setLactacoes(List<LactacaoUnidadeAnimal> lactacoes) {
		this.lactacoes = lactacoes;
	}

	public List<PartoUnidadeAnimal> getPartos() {
		return partos;
	}

	public void setPartos(List<PartoUnidadeAnimal> partos) {
		this.partos = partos;
	}
}
