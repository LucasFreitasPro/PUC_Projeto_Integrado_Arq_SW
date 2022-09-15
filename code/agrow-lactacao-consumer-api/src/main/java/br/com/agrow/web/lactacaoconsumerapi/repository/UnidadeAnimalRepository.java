package br.com.agrow.web.lactacaoconsumerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agrow.web.lactacaoconsumerapi.model.UnidadeAnimal;

public interface UnidadeAnimalRepository extends MongoRepository<UnidadeAnimal, String> {

}
