package br.com.agrow.web.lactacaoconsumerapi.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.agrow.web.lib.dto.CurvaLactacaoResponse;
import br.com.agrow.web.lib.dto.ItemCurvaLactacaoResponse;

@Component
public class CurvaLactacaoLoaderInMemory {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<LocalDate, CurvaLactacaoResponse> curvas;

	public Map<LocalDate, CurvaLactacaoResponse> getCurvas() {
		return curvas;
	}

	@PostConstruct
	private void load() {
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

		int bound = 40;

		curvas = new HashMap<LocalDate, CurvaLactacaoResponse>();

		dates.forEach(d -> {
			List<ItemCurvaLactacaoResponse> itens = new ArrayList<ItemCurvaLactacaoResponse>();
			itens.add(new ItemCurvaLactacaoResponse(0, "0 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "30 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "60 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "90 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "120 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "150 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "180 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "210 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "240 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "270 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "300 dias"));
			itens.add(new ItemCurvaLactacaoResponse(new Random().nextInt(bound), "330 dias"));
			curvas.put(d, new CurvaLactacaoResponse(itens));
		});
		logger.info("Curva Lactção carregada em memoria");
	}
}
