package br.com.agrow.web.lib.dto;

import java.util.List;

public class ColheitaTalhaoResponse {

	private List<ItemColheitaTalhaoResponse> itens;

	public ColheitaTalhaoResponse() {

	}

	public ColheitaTalhaoResponse(List<ItemColheitaTalhaoResponse> itens) {
		this.itens = itens;
	}

	public List<ItemColheitaTalhaoResponse> getItens() {
		return itens;
	}

	public void setItens(List<ItemColheitaTalhaoResponse> itens) {
		this.itens = itens;
	}
}
