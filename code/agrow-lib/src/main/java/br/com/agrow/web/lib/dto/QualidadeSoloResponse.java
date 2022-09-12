package br.com.agrow.web.lib.dto;

import java.util.List;

public class QualidadeSoloResponse {

	private List<ItemSoloResponse> itens;

	public QualidadeSoloResponse() {

	}

	public QualidadeSoloResponse(List<ItemSoloResponse> itens) {
		this.itens = itens;
	}

	public List<ItemSoloResponse> getItens() {
		return itens;
	}

	public void setItens(List<ItemSoloResponse> itens) {
		this.itens = itens;
	}
}
