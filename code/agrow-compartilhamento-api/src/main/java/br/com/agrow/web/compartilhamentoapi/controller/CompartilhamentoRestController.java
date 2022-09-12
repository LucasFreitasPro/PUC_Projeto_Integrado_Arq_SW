package br.com.agrow.web.compartilhamentoapi.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.compartilhamentoapi.model.SharingItem;
import br.com.agrow.web.compartilhamentoapi.service.SharingItemService;
import br.com.agrow.web.lib.dto.DashboardSharingRequest;

@RestController
@RequestMapping("/sharings")
public class CompartilhamentoRestController {

	private final SharingItemService service;

	public CompartilhamentoRestController(SharingItemService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody DashboardSharingRequest dashboardSharingRequest) {
		this.service.save(new SharingItem(dashboardSharingRequest.getFromUserId(), dashboardSharingRequest.getUsersId(), dashboardSharingRequest.getNome(), dashboardSharingRequest.getUsersId().toString().getBytes(), LocalDateTime.now()));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
