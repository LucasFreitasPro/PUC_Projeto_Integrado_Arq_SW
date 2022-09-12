package br.com.agrow.web.rebanhoapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.lib.dto.UnidadeAnimalRegisteringRequest;
import br.com.agrow.web.rebanhoapi.model.UnidadeAnimal;
import br.com.agrow.web.rebanhoapi.repository.UnidadeAnimalRepository;

@Service
public class UnidadeAnimalService {

	private final UnidadeAnimalRepository repository;

	public UnidadeAnimalService(UnidadeAnimalRepository repository) {
		this.repository = repository;
	}

	public Optional<UnidadeAnimal> findById(Long idUnidadeAnimal) {
		return this.repository.findById(idUnidadeAnimal);
	}

	public UnidadeAnimal save(UnidadeAnimalRegisteringRequest unidadeAnimalRegisteringRequest) {
		UnidadeAnimal unidadeAnimal = new UnidadeAnimal();
		unidadeAnimal.setIdentificacao(unidadeAnimalRegisteringRequest.getIdentificacao());
		save(unidadeAnimal);
		return unidadeAnimal;
	}

	@Transactional
	private UnidadeAnimal save(UnidadeAnimal unidadeAnimal) {
		return this.repository.save(unidadeAnimal);
	}
}
