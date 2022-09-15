package br.com.agrow.web.lib.dto;

public class ItemColheitaTalhaoResponse {

	private Double quantidade;
	private String label;

	public ItemColheitaTalhaoResponse() {

	}

	public ItemColheitaTalhaoResponse(Double quantidade, String label) {
		this.quantidade = quantidade;
		this.label = label;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
