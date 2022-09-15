package br.com.agrow.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		Integer statusCode = 0;
		if (status != null) {
			statusCode = Integer.valueOf(status.toString());
			model.addAttribute("statusCode", statusCode);

			if (statusCode == HttpStatus.FORBIDDEN.value()) {
				model.addAttribute("reasonPhrase", HttpStatus.FORBIDDEN.getReasonPhrase());
				model.addAttribute("msg", "Usuário não possui permissão para acessar esta página");
			} else if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("reasonPhrase", HttpStatus.NOT_FOUND.getReasonPhrase());
				model.addAttribute("msg", "A página solicitada não foi encontrada");
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("reasonPhrase", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
				model.addAttribute("msg", "Entre em contato com a Equipe Agrow.");
			} else {
				model.addAttribute("reasonPhrase", HttpStatus.valueOf(statusCode).getReasonPhrase());
				model.addAttribute("msg", "Caso o mesmo persista entre em contato com a Equipe Agrow.");
			}
		}
		return "error/general";
	}
}
