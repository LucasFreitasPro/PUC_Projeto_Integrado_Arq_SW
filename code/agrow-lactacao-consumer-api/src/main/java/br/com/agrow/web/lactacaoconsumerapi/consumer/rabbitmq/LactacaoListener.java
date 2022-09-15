package br.com.agrow.web.lactacaoconsumerapi.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lactacaoconsumerapi.service.UnidadeAnimalService;
import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;

@Component
public class LactacaoListener {

	private final UnidadeAnimalService unidadeAnimalService;

	public LactacaoListener(UnidadeAnimalService unidadeAnimalService) {
		this.unidadeAnimalService = unidadeAnimalService;
	}

	@RabbitListener(queues = "agrow.lactacoes.unidade.animal")
	public void listener(LactacaoUnidadeAnimalRegisteringRequest lactacaoUnidadeAnimalRegisteringRequest) {
		this.unidadeAnimalService.update(lactacaoUnidadeAnimalRegisteringRequest);
	}
}
