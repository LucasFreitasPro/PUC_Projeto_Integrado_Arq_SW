package br.com.agrow.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.agrow.web.lib.dto.CurvaLactacaoResponse;
import br.com.agrow.web.lib.dto.DashboardSharingRequest;
import br.com.agrow.web.lib.dto.ItemCurvaLactacaoResponse;
import br.com.agrow.web.lib.dto.ItemSoloResponse;
import br.com.agrow.web.lib.dto.QualidadeSoloResponse;
import br.com.agrow.web.model.User;
import br.com.agrow.web.service.UserService;

@Controller
@RequestMapping("/dashboards")
public class DashboardController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final String DASHBOARD = "dashboard";
	private final String REDIRECT_DASHBOARDS = "redirect:/dashboards/";

	private final UserService userService;
	private final RestTemplate restTemplate;
	private final SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy-MM");
	private final SimpleDateFormat sdfMesAno = new SimpleDateFormat("MM/yyyy");

	@Value("${url.rebanho.api}")
	private String URL_REBANHO_API;

	@Value("${url.solo.api}")
	private String URL_SOLO_API;

	@Value("${url.rebanho.api}")
	private String URL_GRAFICO3_API;

	@Value("${url.compartilhamento.api}")
	private String URL_COMPARTILHAMENTO_API;

	public DashboardController(UserService userService, RestTemplate restTemplate) {
		this.userService = userService;
		this.restTemplate = restTemplate;
	}

	@GetMapping(path = { "/", "/{mesAno}" })
	public String index(@PathVariable(name = "mesAno", required = false) String mesAno, Model model) {
		String modelMesAno = "";
		if (mesAno == null || mesAno.isEmpty()) {
			mesAno = sdfAnoMes.format(new Date());
			modelMesAno = sdfMesAno.format(new Date());
		} else {
			try {
				Date date = sdfAnoMes.parse(mesAno);
				modelMesAno = sdfMesAno.format(date);
			} catch (ParseException e) {
				model.addAttribute("msgMesAno", "Formato de data inválido");
				model.addAttribute("mesAno", mesAno);
				return DASHBOARD;
			}
		}
		model.addAttribute("mesAno", modelMesAno);

		CurvaLactacaoResponse curvaLactacaoResponse = null;
		try {
			UriComponentsBuilder builderLactacao = UriComponentsBuilder.fromUriString(URL_REBANHO_API).pathSegment("graficos", "curva-lactacao", mesAno);
			curvaLactacaoResponse = restTemplate.getForObject(builderLactacao.build().toUri(), CurvaLactacaoResponse.class);
		} catch (Exception e) {
			curvaLactacaoResponse = new CurvaLactacaoResponse(new ArrayList<ItemCurvaLactacaoResponse>());
			carregarValoresPadraoCurvaLactacao(curvaLactacaoResponse);
			logger.error("Falha ao buscar os dados da Curva de Lactação", e);
		}
		model.addAttribute("chartAreaData", curvaLactacaoResponse.getItens().stream().map(i -> i.getQuantidade()).collect(Collectors.toList()));
		model.addAttribute("chartAreaLabel", curvaLactacaoResponse.getItens().stream().map(i -> i.getDiasEmLactação()).collect(Collectors.toList()));

		QualidadeSoloResponse qualidadeSoloResponse;
		try {
			UriComponentsBuilder builderSolo = UriComponentsBuilder.fromUriString(URL_SOLO_API).pathSegment("solos", "graficos", "qualidade-solo", mesAno);
			qualidadeSoloResponse = restTemplate.getForObject(builderSolo.build().toUri(), QualidadeSoloResponse.class);
		} catch (Exception e) {
			qualidadeSoloResponse = new QualidadeSoloResponse(new ArrayList<>());
			carregarValoresPadraoQualidadeSolo(qualidadeSoloResponse);
			logger.error("Falha ao buscar os dados da Qualidade do Solo", e);
		}
		model.addAttribute("chartBarData", qualidadeSoloResponse.getItens().stream().map(i -> i.getPercentual()).collect(Collectors.toList()));
		model.addAttribute("chartBarLabel", qualidadeSoloResponse.getItens().stream().map(i -> i.getLabel()).collect(Collectors.toList()));

		model.addAttribute("chartPieData", new int[] { 1, 324, 565, 5678 });

		model.addAttribute("users", this.userService.findAll());
		model.addAttribute("sharingRequest", new DashboardSharingRequest(new ArrayList<UUID>()));
		return DASHBOARD;
	}

	@PostMapping("/share")
	public String share(DashboardSharingRequest dashboardSharingRequest, @CurrentSecurityContext(expression = "authentication.name") String username, Model model, RedirectAttributes attributes) {
		Date date = null;
		try {
			date = sdfMesAno.parse(dashboardSharingRequest.getMesAno());

			try {
				User user = this.userService.findByUsername(username).get();
				dashboardSharingRequest.setFromUserId(user.getUserId());
				dashboardSharingRequest.setNome("Agrow Web - Dashboard " + dashboardSharingRequest.getMesAno());

				UriComponentsBuilder builderSharing = UriComponentsBuilder.fromUriString(URL_COMPARTILHAMENTO_API).pathSegment("sharings");
				this.restTemplate.postForEntity(builderSharing.toUriString(), dashboardSharingRequest, Object.class);
				attributes.addFlashAttribute("msgSucesso", "Dashboard compartilhado com sucesso");
				return REDIRECT_DASHBOARDS + sdfAnoMes.format(date);
			} catch (Exception e) {
				attributes.addFlashAttribute("msgErro", "Falha ao compartilhar Dashboard");
				logger.error("Falha ao compartilhar Dashboard", e);
			}
		} catch (ParseException e) {
			attributes.addFlashAttribute("msgErro", "Falha ao compartilhar Dashboard");
			logger.error("Falha ao compartilhar Dashboard", e);
		}
		return REDIRECT_DASHBOARDS;
	}

	private void carregarValoresPadraoCurvaLactacao(CurvaLactacaoResponse curvaLactacaoResponse) {
		List<ItemCurvaLactacaoResponse> itens = new ArrayList<ItemCurvaLactacaoResponse>();
		itens.add(new ItemCurvaLactacaoResponse(0, "0 dias"));
		itens.add(new ItemCurvaLactacaoResponse(20, "30 dias"));
		itens.add(new ItemCurvaLactacaoResponse(40, "60 dias"));
		itens.add(new ItemCurvaLactacaoResponse(60, "90 dias"));
		itens.add(new ItemCurvaLactacaoResponse(80, "120 dias"));
		itens.add(new ItemCurvaLactacaoResponse(100, "150 dias"));
		itens.add(new ItemCurvaLactacaoResponse(60, "180 dias"));
		itens.add(new ItemCurvaLactacaoResponse(40, "210 dias"));
		itens.add(new ItemCurvaLactacaoResponse(80, "240 dias"));
		itens.add(new ItemCurvaLactacaoResponse(120, "270 dias"));
		itens.add(new ItemCurvaLactacaoResponse(120, "300 dias"));
		itens.add(new ItemCurvaLactacaoResponse(90, "330 dias"));
		curvaLactacaoResponse.setItens(itens);
	}

	private void carregarValoresPadraoQualidadeSolo(QualidadeSoloResponse qualidadeSoloResponse) {
		List<ItemSoloResponse> itens = new ArrayList<ItemSoloResponse>();
		itens.add(new ItemSoloResponse(50.0, "PLANTIO DO SOLO"));
		itens.add(new ItemSoloResponse(20.0, "ROTAÇÃO DO SOLO"));
		itens.add(new ItemSoloResponse(30.0, "COBERTURA VERDE"));
		qualidadeSoloResponse.setItens(itens);
	}
}
