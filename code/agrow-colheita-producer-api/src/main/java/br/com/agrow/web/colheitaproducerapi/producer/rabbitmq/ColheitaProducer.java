package br.com.agrow.web.colheitaproducerapi.producer.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@Component
public class ColheitaProducer {

	private final RabbitTemplate rabbitTemplate;

	private final FanoutExchange fanoutExchange;

	public ColheitaProducer(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = fanoutExchange;
	}

	public void send(DronesSoloRegisteringRequest dronesSoloRegisteringRequest) {
		this.rabbitTemplate.convertAndSend(this.fanoutExchange.getName(), "", dronesSoloRegisteringRequest);
	}
}
