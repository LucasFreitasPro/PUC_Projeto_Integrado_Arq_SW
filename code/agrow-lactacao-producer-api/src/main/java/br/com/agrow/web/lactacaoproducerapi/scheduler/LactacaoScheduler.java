package br.com.agrow.web.lactacaoproducerapi.scheduler;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lactacaoproducerapi.producer.rabbitmq.LactacaoProducer;
import br.com.agrow.web.lib.dto.LactacaoUnidadeAnimalRegisteringRequest;

@Component
public class LactacaoScheduler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final LactacaoProducer lactacaoProducer;

	public LactacaoScheduler(LactacaoProducer lactacaoProducer) {
		this.lactacaoProducer = lactacaoProducer;
	}

	@Scheduled(fixedDelayString = "${lactacao.scheduler.delay.minutes}", timeUnit = TimeUnit.MINUTES)
	public void generateData() {
		LactacaoUnidadeAnimalRegisteringRequest request = new LactacaoUnidadeAnimalRegisteringRequest();
		int producao = new Random().nextInt(40);
		request.setProducao(producao);
		if (producao % 2 == 0) {
			request.setIdentificacaoAnimal("VC-HOLANDESA-1");
		} else {
			request.setIdentificacaoAnimal("VC-GIR-13");
		}
		request.setDataProducao(LocalDate.now());
		this.lactacaoProducer.send(request);
		this.logger.info("Dados enviados para a fila: " + request);
	}
}
