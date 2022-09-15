package br.com.agrow.web.dronesconsumerapi.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.dronesconsumerapi.model.Solo;
import br.com.agrow.web.dronesconsumerapi.repository.SoloRepository;
import br.com.agrow.web.lib.dto.ItemSoloResponse;
import br.com.agrow.web.lib.dto.QualidadeSoloResponse;

@Service
public class SoloService {

	private final SoloRepository repository;

	public SoloService(SoloRepository repository) {
		this.repository = repository;
	}

	public QualidadeSoloResponse construirQualidadeSolo(Date anoMes) {
		List<ItemSoloResponse> itens = new ArrayList<ItemSoloResponse>();

		Optional<Solo> optional = findByYearMonth(Instant.ofEpochMilli(anoMes.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
		if (optional.isPresent()) {
			itens.add(new ItemSoloResponse(optional.get().getSoilPlating(), "PLANTIO DO SOLO"));
			itens.add(new ItemSoloResponse(optional.get().getSoilRotation(), "ROTAÇÃO DO SOLO"));
			itens.add(new ItemSoloResponse(optional.get().getGreenCover(), "COBERTURA VERDE"));
		} else {
			itens.add(new ItemSoloResponse(65.0, "PLANTIO DO SOLO"));
			itens.add(new ItemSoloResponse(15.0, "ROTAÇÃO DO SOLO"));
			itens.add(new ItemSoloResponse(20.0, "COBERTURA VERDE"));
		}
		return new QualidadeSoloResponse(itens);
	}

	public List<Solo> findAll() {
		return this.repository.findAll();
	}

	public Optional<Solo> findByYearMonth(LocalDate yearMonth) {
		Solo solo = new Solo();
		solo.setYearMonth(yearMonth);
		return this.repository.findOne(Example.of(solo));
	}

	@Transactional
	public Solo save(Solo solo) {
		return this.repository.save(solo);
	}

	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}
}
