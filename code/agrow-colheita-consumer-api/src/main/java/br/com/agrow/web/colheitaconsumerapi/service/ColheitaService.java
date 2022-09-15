package br.com.agrow.web.colheitaconsumerapi.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrow.web.colheitaconsumerapi.model.Colheita;
import br.com.agrow.web.colheitaconsumerapi.repository.ColheitaRepository;
import br.com.agrow.web.lib.dto.ColheitaTalhaoResponse;
import br.com.agrow.web.lib.dto.ItemColheitaTalhaoResponse;

@Service
public class ColheitaService {

	private final ColheitaRepository repository;

	public ColheitaService(ColheitaRepository repository) {
		this.repository = repository;
	}

	public ColheitaTalhaoResponse construirResposta(Date anoMes) {
		List<ItemColheitaTalhaoResponse> itens = null;

		Optional<Colheita> optional = findByYearMonth(Instant.ofEpochMilli(anoMes.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
		if (optional.isPresent()) {
			itens = List.of(new ItemColheitaTalhaoResponse(optional.get().getCoffee(), "Café"),
					new ItemColheitaTalhaoResponse(optional.get().getSoy(), "Soja"),
					new ItemColheitaTalhaoResponse(optional.get().getCorn(), "Milho"),
					new ItemColheitaTalhaoResponse(optional.get().getSoho(), "Soho"),
					new ItemColheitaTalhaoResponse(optional.get().getVegetables(), "Hortaliças"));
		} else {
			itens = List.of(new ItemColheitaTalhaoResponse(10D, "Café"),
					new ItemColheitaTalhaoResponse(8D, "Soja"),
					new ItemColheitaTalhaoResponse(5D, "Milho"),
					new ItemColheitaTalhaoResponse(7D, "Soho"),
					new ItemColheitaTalhaoResponse(3D, "Hortaliças"));
		}
		return new ColheitaTalhaoResponse(itens);
	}

	public List<Colheita> findAll() {
		return this.repository.findAll();
	}

	public Optional<Colheita> findByYearMonth(LocalDate yearMonth) {
		Colheita colheita = new Colheita();
		colheita.setYearMonth(yearMonth);
		return this.repository.findOne(Example.of(colheita));
	}

	@Transactional
	public void save(Colheita colheita) {
		this.repository.save(colheita);
	}

	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}
}
