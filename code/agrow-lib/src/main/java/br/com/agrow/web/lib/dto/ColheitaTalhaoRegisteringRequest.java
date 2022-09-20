package br.com.agrow.web.lib.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ColheitaTalhaoRegisteringRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double coffee;
	private Double corn;
	private Double soho;
	private Double soy;
	private Double vegetables;
	private LocalDate yearMonth;

	public Double getCoffee() {
		return coffee;
	}

	public void setCoffee(Double coffee) {
		this.coffee = coffee;
	}

	public Double getCorn() {
		return corn;
	}

	public void setCorn(Double corn) {
		this.corn = corn;
	}

	public Double getSoho() {
		return soho;
	}

	public void setSoho(Double soho) {
		this.soho = soho;
	}

	public Double getSoy() {
		return soy;
	}

	public void setSoy(Double soy) {
		this.soy = soy;
	}

	public Double getVegetables() {
		return vegetables;
	}

	public void setVegetables(Double vegetables) {
		this.vegetables = vegetables;
	}

	public LocalDate getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(LocalDate yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Override
	public String toString() {
		return "ColheitaTalhaoRegisteringRequest [coffee=" + coffee + ", corn=" + corn + ", soho=" + soho + ", soy=" + soy + ", vegetables=" + vegetables + ", yearMonth=" + yearMonth + "]";
	}
}
