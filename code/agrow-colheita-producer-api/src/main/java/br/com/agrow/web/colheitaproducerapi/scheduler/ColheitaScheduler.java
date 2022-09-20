package br.com.agrow.web.colheitaproducerapi.scheduler;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.agrow.web.colheitaproducerapi.producer.rabbitmq.ColheitaProducer;
import br.com.agrow.web.lib.dto.ColheitaTalhaoRegisteringRequest;

@Component
public class ColheitaScheduler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ColheitaProducer colheitaProducer;

	public ColheitaScheduler(ColheitaProducer colheitaProducer) {
		this.colheitaProducer = colheitaProducer;
	}

	@Scheduled(fixedDelayString = "${colheita.scheduler.delay.minutes}", timeUnit = TimeUnit.MINUTES)
	public void generateData() {
		double leftLimit = 1D;
		double rightLimit = 50D;

		ColheitaTalhaoRegisteringRequest request = new ColheitaTalhaoRegisteringRequest();
		request.setCoffee(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setCorn(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setSoho(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setSoy(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setVegetables(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
		request.setYearMonth(LocalDate.now());
		this.colheitaProducer.send(request);
		this.logger.info("Dados enviados para a fila: " + request);
	}
}
