package br.com.agrow.web.lactacaoproducerapi.producer.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;

@Component
public class LactacaoProducer {

	private final RabbitTemplate rabbitTemplate;

	private final FanoutExchange fanoutExchange;

	public LactacaoProducer(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = fanoutExchange;
	}

	public void send(LactacaoUnidadeAnimalRegisteringRequest lactacaoUnidadeAnimalRegisteringRequest) {
		this.rabbitTemplate.convertAndSend(this.fanoutExchange.getName(), "", lactacaoUnidadeAnimalRegisteringRequest);
	}
}
