package br.com.agrow.web.colheitaconsumerapi.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.agrow.web.colheitaconsumerapi.model.Colheita;
import br.com.agrow.web.colheitaconsumerapi.service.ColheitaService;

@Component
public class ColheitaLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ColheitaService service;

	public ColheitaLoader(ColheitaService service) {
		this.service = service;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Colheita> all = this.service.findAll();
		if (all.isEmpty()) {
			List<LocalDate> dates = List.of(LocalDate.of(2021, 1, 1),
					LocalDate.of(2021, 2, 1),
					LocalDate.of(2021, 3, 1),
					LocalDate.of(2021, 4, 1),
					LocalDate.of(2021, 5, 1),
					LocalDate.of(2021, 6, 1),
					LocalDate.of(2021, 7, 1),
					LocalDate.of(2021, 8, 1),
					LocalDate.of(2021, 9, 1),
					LocalDate.of(2021, 10, 1),
					LocalDate.of(2021, 11, 1),
					LocalDate.of(2021, 12, 1),
					LocalDate.of(2022, 1, 1),
					LocalDate.of(2022, 2, 1),
					LocalDate.of(2022, 3, 1),
					LocalDate.of(2022, 4, 1),
					LocalDate.of(2022, 5, 1),
					LocalDate.of(2022, 6, 1),
					LocalDate.of(2022, 7, 1),
					LocalDate.of(2022, 8, 1),
					LocalDate.of(2022, 9, 1));

			double leftLimit = 1D;
			double rightLimit = 50D;

			dates.forEach(d -> {
				Colheita colheita = new Colheita(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
						leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
						leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
						leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
						leftLimit + new Random().nextDouble() * (rightLimit - leftLimit),
						d);
				this.service.save(colheita);
				logger.info("Registro inserido com sucesso " + colheita);
			});
		} else {
			logger.info("Banco já possui uma carga com " + all.size() + " registros");
		}
	}
}
