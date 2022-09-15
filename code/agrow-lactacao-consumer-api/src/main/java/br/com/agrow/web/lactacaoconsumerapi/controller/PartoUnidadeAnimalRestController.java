package br.com.agrow.web.lactacaoconsumerapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lactacaoconsumerapi.service.UnidadeAnimalService;
import br.com.agrow.web.lib.dto.PartoUnidadeAnimalRegisteringRequest;

@RestController
@RequestMapping("/unidades-animais/{idUnidadeAnimal}/partos")
public class PartoUnidadeAnimalRestController {

	private final UnidadeAnimalService unidadeAnimalService;

	public PartoUnidadeAnimalRestController(UnidadeAnimalService unidadeAnimalService) {
		this.unidadeAnimalService = unidadeAnimalService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@PathVariable(name = "idUnidadeAnimal", required = true) String idUnidadeAnimal,
			@Valid @RequestBody PartoUnidadeAnimalRegisteringRequest partoUnidadeAnimalRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		partoUnidadeAnimalRegisteringRequest.setIdUnidadeAnimal(idUnidadeAnimal);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.unidadeAnimalService.update(partoUnidadeAnimalRegisteringRequest));
	}
}
