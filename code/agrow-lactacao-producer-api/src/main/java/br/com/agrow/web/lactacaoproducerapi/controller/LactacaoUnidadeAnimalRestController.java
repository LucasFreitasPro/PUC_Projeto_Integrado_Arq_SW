package br.com.agrow.web.lactacaoproducerapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.lactacaoproducerapi.producer.rabbitmq.LactacaoProducer;
import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;

@RestController
@RequestMapping("/lactacoes-unidades-animais")
public class LactacaoUnidadeAnimalRestController {

	private final LactacaoProducer lactacaoProducer;

	public LactacaoUnidadeAnimalRestController(LactacaoProducer lactacaoProducer) {
		this.lactacaoProducer = lactacaoProducer;
	}

	public ResponseEntity<Object> create(@Valid @RequestBody LactacaoUnidadeAnimalRegisteringRequest lactacaoUnidadeAnimalRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		this.lactacaoProducer.send(lactacaoUnidadeAnimalRegisteringRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
