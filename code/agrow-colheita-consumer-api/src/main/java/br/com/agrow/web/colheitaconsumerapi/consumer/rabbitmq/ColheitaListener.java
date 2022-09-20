package br.com.agrow.web.colheitaconsumerapi.consumer.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.colheitaconsumerapi.model.Colheita;
import br.com.agrow.web.colheitaconsumerapi.service.ColheitaService;
import br.com.agrow.web.lib.dto.ColheitaTalhaoRegisteringRequest;

@Component
public class ColheitaListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ColheitaService colheitaService;

	public ColheitaListener(ColheitaService colheitaService) {
		this.colheitaService = colheitaService;
	}

	@RabbitListener(queues = "agrow.colheitas")
	public void listener(ColheitaTalhaoRegisteringRequest colheitaTalhaoRegisteringRequest) {
		this.logger.info("Dados recebidos: " + colheitaTalhaoRegisteringRequest);

		Colheita colheita = new Colheita();
		colheita.setCoffee(colheitaTalhaoRegisteringRequest.getCoffee());
		colheita.setCorn(colheitaTalhaoRegisteringRequest.getCorn());
		colheita.setSoho(colheitaTalhaoRegisteringRequest.getSoho());
		colheita.setSoy(colheitaTalhaoRegisteringRequest.getSoy());
		colheita.setVegetables(colheitaTalhaoRegisteringRequest.getVegetables());
		colheita.setYearMonth(colheitaTalhaoRegisteringRequest.getYearMonth());
		this.colheitaService.save(colheita);
	}
}
