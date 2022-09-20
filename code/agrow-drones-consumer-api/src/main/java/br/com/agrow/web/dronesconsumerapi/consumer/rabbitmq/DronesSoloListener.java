package br.com.agrow.web.dronesconsumerapi.consumer.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.agrow.web.dronesconsumerapi.model.Solo;
import br.com.agrow.web.dronesconsumerapi.service.SoloService;
import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@Component
public class DronesSoloListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final SoloService soloService;

	public DronesSoloListener(SoloService soloService) {
		this.soloService = soloService;
	}

	@RabbitListener(queues = "agrow.drones.solo")
	public void listener(DronesSoloRegisteringRequest dronesSoloRegisteringRequest) {
		this.logger.info("Dados recebidos: " + dronesSoloRegisteringRequest);
		Solo solo = new Solo();
		solo.setGreenCover(dronesSoloRegisteringRequest.getGreenCover());
		solo.setSoilPlating(dronesSoloRegisteringRequest.getSoilPlating());
		solo.setSoilRotation(dronesSoloRegisteringRequest.getSoilRotation());
		solo.setYearMonth(dronesSoloRegisteringRequest.getYearMonth());
		this.soloService.save(solo);
	}
}
