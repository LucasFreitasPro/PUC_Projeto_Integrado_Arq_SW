package br.com.agrow.web.lib.dto;

public class ItemSoloResponse {

	private Double percentual;

	private String label;

	public ItemSoloResponse() {

	}

	public ItemSoloResponse(Double percentual, String label) {
		this.percentual = percentual;
		this.label = label;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
