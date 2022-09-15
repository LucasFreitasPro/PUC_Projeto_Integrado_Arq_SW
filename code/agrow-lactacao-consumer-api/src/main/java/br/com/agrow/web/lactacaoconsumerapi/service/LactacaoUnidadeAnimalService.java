package br.com.agrow.web.lactacaoconsumerapi.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.lactacaoconsumerapi.model.LactacaoUnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.repository.LactacaoUnidadeAnimalRepository;
import br.com.agrow.web.lactacaoconsumerapi.utils.CurvaLactacaoLoaderInMemory;
import br.com.agrow.web.lib.dto.CurvaLactacaoResponse;

@Service
public class LactacaoUnidadeAnimalService {

	private final LactacaoUnidadeAnimalRepository repository;
	private final CurvaLactacaoLoaderInMemory curvaLactacaoLoaderInMemory;

	public LactacaoUnidadeAnimalService(LactacaoUnidadeAnimalRepository repository, CurvaLactacaoLoaderInMemory curvaLactacaoLoaderInMemory) {
		this.repository = repository;
		this.curvaLactacaoLoaderInMemory = curvaLactacaoLoaderInMemory;
	}

	@Transactional
	public void save(LactacaoUnidadeAnimal lactacaoUnidadeAnimal) {
		this.repository.save(lactacaoUnidadeAnimal);
	}

	public CurvaLactacaoResponse construirCurvaLactacao(Date anoMes) {
		LocalDate localDate = Instant.ofEpochMilli(anoMes.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		return this.curvaLactacaoLoaderInMemory.getCurvas().get(localDate);
	}
}
