package br.com.agrow.web.lib.dto;

import java.util.List;

public class CurvaLactacaoResponse {

	private List<ItemCurvaLactacaoResponse> itens;

	public CurvaLactacaoResponse() {

	}

	public CurvaLactacaoResponse(List<ItemCurvaLactacaoResponse> itens) {
		this.itens = itens;
	}

	public List<ItemCurvaLactacaoResponse> getItens() {
		return itens;
	}

	public void setItens(List<ItemCurvaLactacaoResponse> itens) {
		this.itens = itens;
	}
}
