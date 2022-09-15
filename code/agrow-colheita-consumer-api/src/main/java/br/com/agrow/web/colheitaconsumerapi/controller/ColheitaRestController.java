package br.com.agrow.web.colheitaconsumerapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.colheitaconsumerapi.service.ColheitaService;
import br.com.agrow.web.lib.dto.ColheitaTalhaoResponse;

@RestController
@RequestMapping("/colheitas")
public class ColheitaRestController {

	private final SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy-MM");

	private final ColheitaService service;

	public ColheitaRestController(ColheitaService service) {
		this.service = service;
	}

	@GetMapping("/por-talhao/{anoMes}")
	public ColheitaTalhaoResponse curvaDeLactacao(@PathVariable("anoMes") String anoMes) {
		Date anoMesDate = null;
		try {
			anoMesDate = sdfAnoMes.parse(anoMes);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return this.service.construirResposta(anoMesDate);
	}
}
