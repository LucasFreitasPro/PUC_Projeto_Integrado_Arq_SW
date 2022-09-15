package br.com.agrow.web.lib.dto;

import javax.validation.constraints.NotBlank;

public class UnidadeAnimalUpdatingRequest {

	private String idUnidadeAnimal;

	@NotBlank
	private String identificacao;

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
}
