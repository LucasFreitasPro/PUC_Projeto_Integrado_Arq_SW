package br.com.agrow.web.lib.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class LactacaoUnidadeAnimalRegisteringRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String identificacaoAnimal;
	private LocalDate dataProducao;
	private Integer producao;

	public String getIdentificacaoAnimal() {
		return identificacaoAnimal;
	}

	public void setIdentificacaoAnimal(String identificacaoAnimal) {
		this.identificacaoAnimal = identificacaoAnimal;
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
		return "LactacaoUnidadeAnimalRegisteringRequest [identificacaoAnimal=" + identificacaoAnimal + ", dataProducao=" + dataProducao + ", producao=" + producao + "]";
	}
}
