package br.com.agrow.web.rebanhoapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.lib.dto.CurvaLactacaoResponse;
import br.com.agrow.web.lib.dto.ItemCurvaLactacaoResponse;
import br.com.agrow.web.rebanhoapi.model.LactacaoUnidadeAnimal;
import br.com.agrow.web.rebanhoapi.repository.LactacaoUnidadeAnimalRepository;

@Service
public class LactacaoUnidadeAnimalService {

	private final LactacaoUnidadeAnimalRepository repository;

	public LactacaoUnidadeAnimalService(LactacaoUnidadeAnimalRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void save(LactacaoUnidadeAnimal lactacaoUnidadeAnimal) {
		this.repository.save(lactacaoUnidadeAnimal);
	}

	public CurvaLactacaoResponse construirCurvaLactacao(Date anoMesDate) {
		List<ItemCurvaLactacaoResponse> itens = new ArrayList<ItemCurvaLactacaoResponse>();
		itens.add(new ItemCurvaLactacaoResponse(0, "0 dias"));
		itens.add(new ItemCurvaLactacaoResponse(27, "30 dias"));
		itens.add(new ItemCurvaLactacaoResponse(22, "60 dias"));
		itens.add(new ItemCurvaLactacaoResponse(30, "90 dias"));
		itens.add(new ItemCurvaLactacaoResponse(40, "120 dias"));
		itens.add(new ItemCurvaLactacaoResponse(38, "150 dias"));
		itens.add(new ItemCurvaLactacaoResponse(32, "180 dias"));
		itens.add(new ItemCurvaLactacaoResponse(35, "210 dias"));
		itens.add(new ItemCurvaLactacaoResponse(40, "240 dias"));
		itens.add(new ItemCurvaLactacaoResponse(29, "270 dias"));
		itens.add(new ItemCurvaLactacaoResponse(31, "300 dias"));
		itens.add(new ItemCurvaLactacaoResponse(34, "330 dias"));
		return new CurvaLactacaoResponse(itens);
	}
}
