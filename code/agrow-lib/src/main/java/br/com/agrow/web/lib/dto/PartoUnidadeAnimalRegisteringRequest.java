package br.com.agrow.web.lib.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class PartoUnidadeAnimalRegisteringRequest {

	@NotNull
	private LocalDate dataParto;

	public LocalDate getDataParto() {
		return dataParto;
	}

	public void setDataParto(LocalDate dataParto) {
		this.dataParto = dataParto;
	}
}
