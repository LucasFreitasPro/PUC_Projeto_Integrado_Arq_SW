package br.com.agrow.web.lactacaoconsumerapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("animalUnits")
public class UnidadeAnimal {

	@Id
	private String idUnidadeAnimal;

	private String identificacao;

	private List<PartoUnidadeAnimal> partos;

	private List<LactacaoUnidadeAnimal> lactacoes;

	public String getIdUnidadeAnimal() {
		return idUnidadeAnimal;
	}

	public void setIdUnidadeAnimal(String idUnidadeAnimal) {
		this.idUnidadeAnimal = idUnidadeAnimal;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public List<PartoUnidadeAnimal> getPartos() {
		return partos;
	}

	public void setPartos(List<PartoUnidadeAnimal> partos) {
		this.partos = partos;
	}

	public List<LactacaoUnidadeAnimal> getLactacoes() {
		return lactacoes;
	}

	public void setLactacoes(List<LactacaoUnidadeAnimal> lactacoes) {
		this.lactacoes = lactacoes;
	}

	@Override
	public String toString() {
		return "UnidadeAnimal [identificacao=" + identificacao + ", partos=" + partos + ", lactacoes=" + lactacoes + "]";
	}
}
