package br.com.agrow.web.dronesconsumerapi.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@Component
public class DronesSoloListener {

	@RabbitListener(queues = "agrow.drones.solo")
	public void listener(DronesSoloRegisteringRequest dronesSoloRegisteringRequest) {

	}
}
