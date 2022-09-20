package br.com.agrow.web.dronesproducerapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrow.web.dronesproducerapi.producer.rabbitmq.DronesSoloProducer;
import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@RestController
@RequestMapping("/drones/solo")
public class DronesSoloRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final DronesSoloProducer dronesSoloProducer;

	public DronesSoloRestController(DronesSoloProducer dronesSoloProducer) {
		this.dronesSoloProducer = dronesSoloProducer;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody DronesSoloRegisteringRequest dronesSoloRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		this.dronesSoloProducer.send(dronesSoloRegisteringRequest);
		this.logger.info("Dados enviados para a fila: " + dronesSoloRegisteringRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
