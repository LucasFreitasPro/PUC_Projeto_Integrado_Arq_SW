package br.com.agrow.web.lactacaoconsumerapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LactacaoUnidadeAnimal {

	private LocalDate dataProducao;
	private Integer producao;
	private LocalDateTime dataRegistro;

	public LactacaoUnidadeAnimal(LocalDate dataProducao, Integer producao, LocalDateTime dataRegistro) {
		this.dataProducao = dataProducao;
		this.producao = producao;
		this.dataRegistro = dataRegistro;
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
}
