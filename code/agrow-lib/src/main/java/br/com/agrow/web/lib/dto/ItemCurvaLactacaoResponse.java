package br.com.agrow.web.lib.dto;

public class ItemCurvaLactacaoResponse {

	// em Kg
	private Integer quantidade;

	private String diasEmLactação;

	public ItemCurvaLactacaoResponse() {

	}

	public ItemCurvaLactacaoResponse(Integer quantidade, String diasEmLactação) {
		this.quantidade = quantidade;
		this.diasEmLactação = diasEmLactação;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDiasEmLactação() {
		return diasEmLactação;
	}

	public void setDiasEmLactação(String diasEmLactação) {
		this.diasEmLactação = diasEmLactação;
	}

	@Override
	public String toString() {
		return "ItemCurvaLactacaoResponse [quantidade=" + quantidade + ", diasEmLactação=" + diasEmLactação + "]";
	}
}
