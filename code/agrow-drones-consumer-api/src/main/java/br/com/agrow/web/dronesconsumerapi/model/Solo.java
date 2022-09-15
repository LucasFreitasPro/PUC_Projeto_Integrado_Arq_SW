package br.com.agrow.web.dronesconsumerapi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("soils")
public class Solo {

	@Id
	private String id;
	private Double greenCover;
	private Double soilPlating;
	private Double soilRotation;
	private LocalDate yearMonth;

	public Solo() {

	}

	public Solo(Double greenCover, Double soilPlating, Double soilRotation, LocalDate yearMonth) {
		this.greenCover = greenCover;
		this.soilPlating = soilPlating;
		this.soilRotation = soilRotation;
		this.yearMonth = yearMonth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getGreenCover() {
		return greenCover;
	}

	public void setGreenCover(Double greenCover) {
		this.greenCover = greenCover;
	}

	public Double getSoilPlating() {
		return soilPlating;
	}

	public void setSoilPlating(Double soilPlating) {
		this.soilPlating = soilPlating;
	}

	public Double getSoilRotation() {
		return soilRotation;
	}

	public void setSoilRotation(Double soilRotation) {
		this.soilRotation = soilRotation;
	}

	public LocalDate getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(LocalDate yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Override
	public String toString() {
		return "Solo [greenCover=" + greenCover + ", soilPlating=" + soilPlating + ", soilRotation=" + soilRotation + ", yearMonth=" + yearMonth + "]";
	}
}
