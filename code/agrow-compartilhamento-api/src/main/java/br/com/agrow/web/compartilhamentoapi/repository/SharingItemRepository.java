package br.com.agrow.web.compartilhamentoapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agrow.web.compartilhamentoapi.model.SharingItem;

public interface SharingItemRepository extends MongoRepository<SharingItem, String> {

}
