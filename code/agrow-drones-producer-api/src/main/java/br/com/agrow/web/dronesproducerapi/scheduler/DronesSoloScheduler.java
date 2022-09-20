package br.com.agrow.web.dronesproducerapi.scheduler;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.agrow.web.dronesproducerapi.producer.rabbitmq.DronesSoloProducer;
import br.com.agrow.web.lib.dto.DronesSoloRegisteringRequest;

@Component
public class DronesSoloScheduler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final DronesSoloProducer dronesSoloProducer;

	public DronesSoloScheduler(DronesSoloProducer dronesSoloProducer) {
		this.dronesSoloProducer = dronesSoloProducer;
	}

	@Scheduled(fixedDelayString = "${drones.solo.scheduler.delay.minutes}", timeUnit = TimeUnit.MINUTES)
	public void generateData() {
		double leftLimit = 1D;
		double rightLimit = 100D;

		DronesSoloRegisteringRequest request = new DronesSoloRegisteringRequest();
		request.setGreenCover(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setSoilPlating(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setSoilRotation(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setYearMonth(LocalDate.now());
		this.dronesSoloProducer.send(request);
		this.logger.info("Dados enviados para a fila: " + request);
	}
}
