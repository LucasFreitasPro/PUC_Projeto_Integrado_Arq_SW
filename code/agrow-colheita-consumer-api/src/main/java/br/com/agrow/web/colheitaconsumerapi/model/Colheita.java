package br.com.agrow.web.colheitaconsumerapi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("harvests")
public class Colheita {

	@Id
	private String id;
	private Double coffee;
	private Double corn;
	private Double soho;
	private Double soy;
	private Double vegetables;
	private LocalDate yearMonth;

	public Colheita() {

	}

	public Colheita(Double coffee, Double corn, Double soho, Double soy, Double vegetables, LocalDate yearMonth) {
		this.coffee = coffee;
		this.corn = corn;
		this.soho = soho;
		this.soy = soy;
		this.vegetables = vegetables;
		this.yearMonth = yearMonth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
}
