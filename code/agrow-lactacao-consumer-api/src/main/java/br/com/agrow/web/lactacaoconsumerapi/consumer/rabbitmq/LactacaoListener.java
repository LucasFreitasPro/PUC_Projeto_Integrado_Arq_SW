package br.com.agrow.web.lactacaoconsumerapi.consumer.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lactacaoconsumerapi.service.UnidadeAnimalService;
import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;

@Component
public class LactacaoListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UnidadeAnimalService unidadeAnimalService;

	public LactacaoListener(UnidadeAnimalService unidadeAnimalService) {
		this.unidadeAnimalService = unidadeAnimalService;
	}

	@RabbitListener(queues = "agrow.lactacoes.unidade.animal")
	public void listener(LactacaoUnidadeAnimalRegisteringRequest lactacaoUnidadeAnimalRegisteringRequest) {
		this.logger.info("Dados recebidos: " + lactacaoUnidadeAnimalRegisteringRequest);
		this.unidadeAnimalService.update(lactacaoUnidadeAnimalRegisteringRequest);
	}
}
