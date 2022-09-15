package br.com.agrow.web.lactacaoconsumerapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lactacaoconsumerapi.service.LactacaoUnidadeAnimalService;
import br.com.agrow.web.lib.dto.CurvaLactacaoResponse;

@RestController
@RequestMapping("/graficos")
public class CurvaLactacaoRestController {

	private final LactacaoUnidadeAnimalService service;
	private final SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy-MM");

	public CurvaLactacaoRestController(LactacaoUnidadeAnimalService service) {
		this.service = service;
	}

	@GetMapping("/curva-lactacao/{anoMes}")
	public CurvaLactacaoResponse curvaDeLactacao(@PathVariable("anoMes") String anoMes) {
		Date anoMesDate = null;
		try {
			anoMesDate = sdfAnoMes.parse(anoMes);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CurvaLactacaoResponse curvaLactacaoResponse = this.service.construirCurvaLactacao(anoMesDate);
		return curvaLactacaoResponse;
	}
}
