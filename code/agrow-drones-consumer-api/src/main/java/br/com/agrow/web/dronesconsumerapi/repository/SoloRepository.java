package br.com.agrow.web.dronesconsumerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agrow.web.dronesconsumerapi.model.Solo;

public interface SoloRepository extends MongoRepository<Solo, String> {

}
