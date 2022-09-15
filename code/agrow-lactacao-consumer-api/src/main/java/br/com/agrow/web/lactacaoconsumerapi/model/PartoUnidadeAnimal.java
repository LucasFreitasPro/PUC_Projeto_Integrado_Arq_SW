package br.com.agrow.web.lactacaoconsumerapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PartoUnidadeAnimal {

	private LocalDate dataParto;

	private LocalDateTime dataRegistro;

	public PartoUnidadeAnimal(LocalDate dataParto, LocalDateTime dataRegistro) {
		this.dataParto = dataParto;
		this.dataRegistro = dataRegistro;
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
