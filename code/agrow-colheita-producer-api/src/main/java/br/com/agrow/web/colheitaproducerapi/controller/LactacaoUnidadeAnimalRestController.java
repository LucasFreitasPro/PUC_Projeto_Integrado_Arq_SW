package br.com.agrow.web.colheitaproducerapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.colheitaproducerapi.producer.rabbitmq.ColheitaProducer;
import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@RestController
@RequestMapping("/drones/solo")
public class LactacaoUnidadeAnimalRestController {

	private final ColheitaProducer lactacaoProducer;

	public LactacaoUnidadeAnimalRestController(ColheitaProducer lactacaoProducer) {
		this.lactacaoProducer = lactacaoProducer;
	}

	public ResponseEntity<Object> create(@Valid @RequestBody DronesSoloRegisteringRequest dronesSoloRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		this.lactacaoProducer.send(dronesSoloRegisteringRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
