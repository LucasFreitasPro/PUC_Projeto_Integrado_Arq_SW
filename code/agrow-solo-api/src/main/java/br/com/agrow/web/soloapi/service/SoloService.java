package br.com.agrow.web.soloapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.agrow.web.lib.dto.ItemSoloResponse;
import br.com.agrow.web.lib.dto.QualidadeSoloResponse;

@Service
public class SoloService {

	public QualidadeSoloResponse construirQualidadeSolo(Date anoMes) {
		List<ItemSoloResponse> itens = new ArrayList<ItemSoloResponse>();
		itens.add(new ItemSoloResponse(65.0, "PLANTIO DO SOLO"));
		itens.add(new ItemSoloResponse(15.0, "ROTAÇÃO DO SOLO"));
		itens.add(new ItemSoloResponse(20.0, "COBERTURA VERDE"));
		return new QualidadeSoloResponse(itens);
	}
}
