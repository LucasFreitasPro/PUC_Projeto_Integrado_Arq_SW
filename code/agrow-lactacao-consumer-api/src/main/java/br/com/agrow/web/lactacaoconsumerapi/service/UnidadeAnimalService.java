package br.com.agrow.web.lactacaoconsumerapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.lactacaoconsumerapi.model.LactacaoUnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.model.PartoUnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.model.UnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.repository.UnidadeAnimalRepository;
import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;
import br.com.agrow.web.lib.dto.PartoUnidadeAnimalRegisteringRequest;
import br.com.agrow.web.lib.dto.UnidadeAnimalRegisteringRequest;
import br.com.agrow.web.lib.dto.UnidadeAnimalUpdatingRequest;

@Service
public class UnidadeAnimalService {

	private final UnidadeAnimalRepository repository;

	public UnidadeAnimalService(UnidadeAnimalRepository repository) {
		this.repository = repository;
	}

	public Optional<UnidadeAnimal> findById(String idUnidadeAnimal) {
		return this.repository.findById(idUnidadeAnimal);
	}

	public Optional<UnidadeAnimal> findByIdentificacao(String identificacao) {
		UnidadeAnimal ua = new UnidadeAnimal();
		ua.setIdentificacao(identificacao);
		return this.repository.findOne(Example.of(ua));
	}

	public List<UnidadeAnimal> findAll() {
		return this.repository.findAll();
	}

	public UnidadeAnimal create(UnidadeAnimalRegisteringRequest unidadeAnimalRegisteringRequest) {
		UnidadeAnimal unidadeAnimal = new UnidadeAnimal();
		unidadeAnimal.setIdentificacao(unidadeAnimalRegisteringRequest.getIdentificacao());
		if (unidadeAnimalRegisteringRequest.getDataUltimoParto() != null) {
			unidadeAnimal.setPartos(new ArrayList<PartoUnidadeAnimal>());
			unidadeAnimal.getPartos().add(new PartoUnidadeAnimal(unidadeAnimalRegisteringRequest.getDataUltimoParto(), LocalDateTime.now()));
		}
		return save(unidadeAnimal);
	}

	public UnidadeAnimal update(UnidadeAnimalUpdatingRequest unidadeAnimalUpdatingRequest) {
		UnidadeAnimal unidadeAnimal = findById(unidadeAnimalUpdatingRequest.getIdUnidadeAnimal()).orElseThrow(() -> new RuntimeException("Unidade Animal não encontrada"));
		unidadeAnimal.setIdentificacao(unidadeAnimalUpdatingRequest.getIdentificacao());
		return save(unidadeAnimal);
	}

	public UnidadeAnimal update(PartoUnidadeAnimalRegisteringRequest partoUnidadeAnimalRegisteringRequest) {
		UnidadeAnimal unidadeAnimal = findById(partoUnidadeAnimalRegisteringRequest.getIdUnidadeAnimal()).orElseThrow(() -> new RuntimeException("Unidade Animal não encontrada"));
		if (unidadeAnimal.getPartos() == null) {
			unidadeAnimal.setPartos(new ArrayList<PartoUnidadeAnimal>());
		}
		unidadeAnimal.getPartos().add(new PartoUnidadeAnimal(partoUnidadeAnimalRegisteringRequest.getDataParto(), LocalDateTime.now()));
		return save(unidadeAnimal);
	}

	public void update(LactacaoUnidadeAnimalRegisteringRequest lactacaoUnidadeAnimalRegisteringRequest) {
		UnidadeAnimal unidadeAnimal = findByIdentificacao(lactacaoUnidadeAnimalRegisteringRequest.getIdentificacaoAnimal()).orElseThrow(() -> new RuntimeException("Unidade Animal não encontrada"));
		if (unidadeAnimal.getLactacoes() == null) {
			unidadeAnimal.setLactacoes(new ArrayList<LactacaoUnidadeAnimal>());
		}
		unidadeAnimal.getLactacoes().add(new LactacaoUnidadeAnimal(lactacaoUnidadeAnimalRegisteringRequest.getDataProducao(), lactacaoUnidadeAnimalRegisteringRequest.getProducao(), LocalDateTime.now()));
		save(unidadeAnimal);
	}

	@Transactional
	public UnidadeAnimal save(UnidadeAnimal unidadeAnimal) {
		return this.repository.save(unidadeAnimal);
	}

	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}
}
