package br.com.agrow.web.rebanhoapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lib.dto.PartoUnidadeAnimalRegisteringRequest;
import br.com.agrow.web.rebanhoapi.model.PartoUnidadeAnimal;
import br.com.agrow.web.rebanhoapi.service.PartoUnidadeAnimalService;

@RestController
@RequestMapping("/unidades-animais/{idUnidadeAnimal}/partos")
public class PartoUnidadeAnimalRestController {

	private final PartoUnidadeAnimalService service;

	public PartoUnidadeAnimalRestController(PartoUnidadeAnimalService partoUnidadeAnimalService) {
		this.service = partoUnidadeAnimalService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@PathVariable(name = "idUnidadeAnimal", required = true) Long idUnidadeAnimal,
			@Valid @RequestBody PartoUnidadeAnimalRegisteringRequest partoUnidadeAnimalRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		PartoUnidadeAnimal partoUnidadeAnimal = this.service.save(partoUnidadeAnimalRegisteringRequest, idUnidadeAnimal);
		return ResponseEntity.status(HttpStatus.CREATED).body(partoUnidadeAnimal);
	}
}
