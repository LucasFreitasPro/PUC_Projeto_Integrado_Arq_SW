package br.com.agrow.web.lib.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class UnidadeAnimalRegisteringRequest {

	@NotBlank
	private String identificacao;

	private LocalDate dataUltimoParto;

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public LocalDate getDataUltimoParto() {
		return dataUltimoParto;
	}

	public void setDataUltimoParto(LocalDate dataUltimoParto) {
		this.dataUltimoParto = dataUltimoParto;
	}
}
