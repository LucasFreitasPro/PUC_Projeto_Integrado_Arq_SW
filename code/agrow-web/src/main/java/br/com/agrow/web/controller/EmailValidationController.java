package br.com.agrow.web.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.agrow.web.model.User;
import br.com.agrow.web.service.UserService;

@Controller
@RequestMapping("/email-validation")
public class EmailValidationController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserService userService;

	public EmailValidationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/validate/{key}")
	public String validate(@PathVariable("key") UUID key, Model model) {
		Optional<User> optUser = this.userService.findByEnabledAndEmailValidationAndEmailValidationKey(false, false, key);
		if (optUser.isPresent()) {
			User user = optUser.get();
			LocalDateTime now = LocalDateTime.now();
			if (now.compareTo(user.getExpirationEmailValidation()) < 0) {
				user.setEnabled(Boolean.TRUE);
				user.setEmailValidation(Boolean.TRUE);
				user.setExpirationEmailValidation(null);
				user.setEmailValidationKey(null);
				user.setEmailValidationTime(now);
				this.userService.save(user);

				model.addAttribute("success", true);
				model.addAttribute("msg", "Verificação de e-mail realizada com sucesso. ");
				logger.info("Verificação de e-mail realizada com sucesso: " + user.getUsername());
			} else {
				model.addAttribute("success", false);
				model.addAttribute("msg", "O código de verificação de e-mail expirou");
				logger.warn("O código de verificação de e-mail expirou: " + user.getEmailValidationKey());
			}
		} else {
			model.addAttribute("success", false);
			model.addAttribute("msg", "O código de verificação informado não existe");
			logger.warn("O código de verificação informado não existe: " + key);
		}

		return "/email/validation-result";
	}
}
