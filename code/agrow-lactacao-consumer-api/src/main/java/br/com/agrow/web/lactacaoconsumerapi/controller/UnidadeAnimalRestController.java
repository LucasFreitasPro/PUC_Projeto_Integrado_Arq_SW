package br.com.agrow.web.lactacaoconsumerapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lactacaoconsumerapi.model.UnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.service.UnidadeAnimalService;
import br.com.agrow.web.lib.dto.UnidadeAnimalRegisteringRequest;
import br.com.agrow.web.lib.dto.UnidadeAnimalUpdatingRequest;

@RestController
@RequestMapping("/unidades-animais")
public class UnidadeAnimalRestController {

	private final UnidadeAnimalService service;

	public UnidadeAnimalRestController(UnidadeAnimalService unidadeAnimalService) {
		this.service = unidadeAnimalService;
	}

	@GetMapping
	public ResponseEntity<Object> getAll() {
		List<UnidadeAnimal> list = this.service.findAll();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{idUnidadeAnimal}")
	public ResponseEntity<Object> getOne(@PathVariable("idUnidadeAnimal") String idUnidadeAnimal) {
		Optional<UnidadeAnimal> optional = this.service.findById(idUnidadeAnimal);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody UnidadeAnimalRegisteringRequest unidadeAnimalRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(unidadeAnimalRegisteringRequest));
	}

	@PutMapping
	public ResponseEntity<Object> update(@Valid @RequestBody UnidadeAnimalUpdatingRequest unidadeAnimalUpdatingRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(unidadeAnimalUpdatingRequest));
	}
}
