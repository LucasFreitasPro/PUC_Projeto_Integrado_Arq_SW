package br.com.agrow.web.rebanhoapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lib.dto.UnidadeAnimalRegisteringRequest;
import br.com.agrow.web.rebanhoapi.model.UnidadeAnimal;
import br.com.agrow.web.rebanhoapi.service.PartoUnidadeAnimalService;
import br.com.agrow.web.rebanhoapi.service.UnidadeAnimalService;

@RestController
@RequestMapping("/unidades-animais")
public class UnidadeAnimalRestController {

	private final UnidadeAnimalService service;
	private final PartoUnidadeAnimalService partoUnidadeAnimalService;

	public UnidadeAnimalRestController(UnidadeAnimalService unidadeAnimalService, PartoUnidadeAnimalService partoUnidadeAnimalService) {
		this.service = unidadeAnimalService;
		this.partoUnidadeAnimalService = partoUnidadeAnimalService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody UnidadeAnimalRegisteringRequest unidadeAnimalRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		UnidadeAnimal unidadeAnimal = this.service.save(unidadeAnimalRegisteringRequest);

		if (unidadeAnimalRegisteringRequest.getDataUltimoParto() != null) {
			this.partoUnidadeAnimalService.save(unidadeAnimalRegisteringRequest.getDataUltimoParto(), unidadeAnimal.getIdUnidadeAnimal());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeAnimal);
	}
}
