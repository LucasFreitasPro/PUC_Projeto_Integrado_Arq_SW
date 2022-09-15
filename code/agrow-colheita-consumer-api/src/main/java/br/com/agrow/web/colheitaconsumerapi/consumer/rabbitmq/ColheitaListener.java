package br.com.agrow.web.colheitaconsumerapi.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@Component
public class ColheitaListener {

	@RabbitListener(queues = "agrow.colheitas")
	public void listener(DronesSoloRegisteringRequest dronesSoloRegisteringRequest) {

	}
}
