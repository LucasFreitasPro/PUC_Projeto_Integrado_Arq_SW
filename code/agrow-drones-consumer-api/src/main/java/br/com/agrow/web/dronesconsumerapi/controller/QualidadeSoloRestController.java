package br.com.agrow.web.dronesconsumerapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.dronesconsumerapi.service.SoloService;
import br.com.agrow.web.lib.dto.QualidadeSoloResponse;

@RestController
@RequestMapping("/solos/graficos")
public class QualidadeSoloRestController {

	private final SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy-MM");

	private final SoloService service;

	public QualidadeSoloRestController(SoloService service) {
		this.service = service;
	}

	@GetMapping("/qualidade-solo/{anoMes}")
	public QualidadeSoloResponse curvaDeLactacao(@PathVariable("anoMes") String anoMes) {
		Date anoMesDate = null;
		try {
			anoMesDate = sdfAnoMes.parse(anoMes);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return this.service.construirQualidadeSolo(anoMesDate);
	}
}
