package br.com.agrow.web.lib.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class PartoUnidadeAnimalRegisteringRequest {

	private String idUnidadeAnimal;

	@NotNull
	private LocalDate dataParto;

	public LocalDate getDataParto() {
		return dataParto;
	}

	public void setDataParto(LocalDate dataParto) {
		this.dataParto = dataParto;
	}

	public String getIdUnidadeAnimal() {
		return idUnidadeAnimal;
	}

	public void setIdUnidadeAnimal(String idUnidadeAnimal) {
		this.idUnidadeAnimal = idUnidadeAnimal;
	}
}
