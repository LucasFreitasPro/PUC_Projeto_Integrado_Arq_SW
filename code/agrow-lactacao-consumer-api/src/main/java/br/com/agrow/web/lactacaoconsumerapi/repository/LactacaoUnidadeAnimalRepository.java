package br.com.agrow.web.lactacaoconsumerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agrow.web.lactacaoconsumerapi.model.LactacaoUnidadeAnimal;

public interface LactacaoUnidadeAnimalRepository extends MongoRepository<LactacaoUnidadeAnimal, Long> {

}
