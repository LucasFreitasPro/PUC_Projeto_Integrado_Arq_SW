package br.com.agrow.web.lib.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class DronesSoloRegisteringRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double greenCover;
	private Double soilPlating;
	private Double soilRotation;
	private LocalDate yearMonth;

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
		return "DronesSoloRegisteringRequest [greenCover=" + greenCover + ", soilPlating=" + soilPlating + ", soilRotation=" + soilRotation + ", yearMonth=" + yearMonth + "]";
	}
}
