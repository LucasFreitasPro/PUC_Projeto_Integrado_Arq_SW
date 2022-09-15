package br.com.agrow.web.lib.dto;

import java.time.LocalDate;

public class LactacaoUnidadeAnimalRegisteringRequest {

	private String idUnidadeAnimal;
	private LocalDate dataProducao;
	private Integer producao;

	public String getIdUnidadeAnimal() {
		return idUnidadeAnimal;
	}

	public void setIdUnidadeAnimal(String idUnidadeAnimal) {
		this.idUnidadeAnimal = idUnidadeAnimal;
	}

	public LocalDate getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(LocalDate dataProducao) {
		this.dataProducao = dataProducao;
	}

	public Integer getProducao() {
		return producao;
	}

	public void setProducao(Integer producao) {
		this.producao = producao;
	}

	@Override
	public String toString() {
		return "LactacaoUnidadeAnimalRegisteringRequest [idUnidadeAnimal=" + idUnidadeAnimal + ", dataProducao=" + dataProducao + ", producao=" + producao + "]";
	}
}
