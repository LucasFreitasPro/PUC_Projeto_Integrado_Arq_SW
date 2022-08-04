package br.com.agrow.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.agrow.web.model.User;
import br.com.agrow.web.service.UserService;

@Controller
@RequestMapping("/agrow")
public class AgrowController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserService userService;

	public AgrowController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/index")
	public String index(@CurrentSecurityContext(expression = "authentication.name") String username, HttpSession httpSession) {
		logger.info("Login realizado com sucesso. Usuario: " + username);

		User user = this.userService.findByUsername(username).get();
		httpSession.setAttribute("isUserLogged", true);
		httpSession.setAttribute("loggedUser", user.getFirstName() + " " + user.getLastName());
		return "/home";
	}
}
