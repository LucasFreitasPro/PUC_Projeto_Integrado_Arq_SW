package br.com.agrow.web.colheitaproducerapi.controller;

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

import br.com.agrow.web.colheitaproducerapi.producer.rabbitmq.ColheitaProducer;
import br.com.agrow.web.lib.dto.ColheitaTalhaoRegisteringRequest;

@RestController
@RequestMapping("/colheitas/talhao")
public class ColheitaTalhaoRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ColheitaProducer colheitaProducer;

	public ColheitaTalhaoRestController(ColheitaProducer colheitaProducer) {
		this.colheitaProducer = colheitaProducer;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ColheitaTalhaoRegisteringRequest colheitaTalhaoRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		this.colheitaProducer.send(colheitaTalhaoRegisteringRequest);
		this.logger.info("Dados enviados para a fila: " + colheitaTalhaoRegisteringRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
