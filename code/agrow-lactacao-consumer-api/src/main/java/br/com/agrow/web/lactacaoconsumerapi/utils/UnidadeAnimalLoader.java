package br.com.agrow.web.lactacaoconsumerapi.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lactacaoconsumerapi.model.LactacaoUnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.model.PartoUnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.model.UnidadeAnimal;
import br.com.agrow.web.lactacaoconsumerapi.service.UnidadeAnimalService;

@Component
public class UnidadeAnimalLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UnidadeAnimalService service;

	public UnidadeAnimalLoader(UnidadeAnimalService service) {
		this.service = service;
	}

	@Override
	public void run(String... args) throws Exception {
		List<UnidadeAnimal> all = this.service.findAll();
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

			for (int i = 0; i < dates.size(); i++) {
				UnidadeAnimal ua = new UnidadeAnimal();
				ua.setIdentificacao("VC-" + (dates.get(i).getYear() == 2021 ? "HOLANDESA-" : "GIR-") + (i + 1));
				ua.setPartos(List.of(new PartoUnidadeAnimal(LocalDate.of(dates.get(i).getYear() - 7, 2, 1), LocalDateTime.now())));
				ua.setLactacoes(List.of(new LactacaoUnidadeAnimal(dates.get(i), i + 3, LocalDateTime.now())));
				this.service.save(ua);
				logger.info("Registro inserido com sucesso " + ua);
			}
		} else {
			logger.info("Banco já possui uma carga com " + all.size() + " registros");
		}
	}
}
