package br.com.agrow.web.compartilhamentoapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.compartilhamentoapi.model.SharingItem;
import br.com.agrow.web.compartilhamentoapi.repository.SharingItemRepository;

@Service
public class SharingItemService {

	private final SharingItemRepository repository;

	public SharingItemService(SharingItemRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void save(SharingItem sharingItem) {
		this.repository.save(sharingItem);
	}
}
