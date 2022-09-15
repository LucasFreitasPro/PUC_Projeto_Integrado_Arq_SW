package br.com.agrow.web.colheitaconsumerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agrow.web.colheitaconsumerapi.model.Colheita;

public interface ColheitaRepository extends MongoRepository<Colheita, String> {

}
