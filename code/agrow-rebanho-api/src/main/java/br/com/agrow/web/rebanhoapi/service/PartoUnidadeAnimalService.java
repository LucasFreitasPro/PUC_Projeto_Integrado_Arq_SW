package br.com.agrow.web.rebanhoapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.lib.dto.PartoUnidadeAnimalRegisteringRequest;
import br.com.agrow.web.rebanhoapi.model.PartoUnidadeAnimal;
import br.com.agrow.web.rebanhoapi.repository.PartoUnidadeAnimalRepository;

@Service
public class PartoUnidadeAnimalService {

	private final PartoUnidadeAnimalRepository repository;
	private final UnidadeAnimalService unidadeAnimalService;

	public PartoUnidadeAnimalService(PartoUnidadeAnimalRepository repository, UnidadeAnimalService unidadeAnimalService) {
		this.repository = repository;
		this.unidadeAnimalService = unidadeAnimalService;
	}

	public PartoUnidadeAnimal save(PartoUnidadeAnimalRegisteringRequest partoUnidadeAnimalRegisteringRequest, Long idUnidadeAnimal) {
		return save(partoUnidadeAnimalRegisteringRequest.getDataParto(), idUnidadeAnimal);
	}

	public PartoUnidadeAnimal save(LocalDate dataParto, Long idUnidadeAnimal) {
		PartoUnidadeAnimal partoUnidadeAnimal = new PartoUnidadeAnimal();
		partoUnidadeAnimal.setDataParto(dataParto);
		partoUnidadeAnimal.setDataRegistro(LocalDateTime.now());
		partoUnidadeAnimal.setUnidadeAnimal(this.unidadeAnimalService.findById(idUnidadeAnimal).orElseThrow(() -> new EntityNotFoundException("Unidade Animal não encontrada")));
		return save(partoUnidadeAnimal);
	}

	@Transactional
	private PartoUnidadeAnimal save(PartoUnidadeAnimal partoUnidadeAnimal) {
		return this.repository.save(partoUnidadeAnimal);
	}
}
