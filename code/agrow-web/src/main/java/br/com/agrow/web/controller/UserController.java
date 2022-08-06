package br.com.agrow.web.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agrow.web.email.EmailSendingService;
import br.com.agrow.web.model.Role;
import br.com.agrow.web.model.User;
import br.com.agrow.web.service.RoleService;
import br.com.agrow.web.service.UserService;
import br.com.agrow.web.utils.EmailUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final String USER_LIST = "user/list";
	private final String USER_FORM = "user/form";
	private final String USER_REGISTER = "user/register";
	private final String REDIRECT_USERS = "redirect:/users/";

	private final EmailUtil emailUtil;
	private final UserService userService;
	private final RoleService roleService;
	private final EmailSendingService emailSendingService;

	@Value("${send.email.when.registering.user}")
	private boolean sendEmailWhenRegisteringUser;

	public UserController(EmailUtil emailUtil, UserService userService, RoleService roleService, EmailSendingService emailSendingService) {
		this.emailUtil = emailUtil;
		this.userService = userService;
		this.roleService = roleService;
		this.emailSendingService = emailSendingService;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", this.userService.findAll());
		return USER_LIST;
	}

	@GetMapping("/prepare-to-register")
	public String prepareToRegister(Model model) {
		model.addAttribute("user", new User());
		return USER_REGISTER;
	}

	@PostMapping("/register")
	public String register(@Valid User user, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			logger.warn("Erro na validação do formulário de cadastro de usuário");
			return USER_REGISTER;
		}
		if (!user.getPassword().equals(user.getRepeatedPassword())) {
			result.addError(new FieldError("user", "password", "As senhas informadas não são compatíveis"));
			logger.warn("As senhas informadas não são compatíveis");
			return USER_REGISTER;
		}

		if (this.userService.findByUsername(user.getUsername()).isPresent()) {
			result.addError(new FieldError("user", "username", "O e-mail " + user.getUsername() + " já está cadastrado no sistema"));
			logger.warn("O e-mail " + user.getUsername() + " já está cadastrado no sistema");
			return USER_REGISTER;
		}
		user.setExpirationEmailValidation(LocalDateTime.now().plusHours(1));
		user.setEmailValidationKey(UUID.randomUUID());

		if (sendEmailWhenRegisteringUser) {
			CompletableFuture.runAsync(() -> {
				this.emailSendingService.sendEmail(this.emailUtil.loadEmailSendingRequest(user));
			});
		}

		this.userService.register(user);

		attributes.addFlashAttribute("registered", true);

		logger.info("Usuário " + user.getUsername() + " registrado com sucesso");

		return REDIRECT_USERS + "prepare-to-register";
	}

	@GetMapping("/prepare-to-edit")
	public String prepareToEdit(@RequestParam("username") String username, Model model) {
		User user = this.userService.findByUsername(username).get();
		model.addAttribute("user", user);

		model.addAttribute("roles", this.roleService.findAll());

		return USER_FORM;
	}

	@PostMapping("/edit")
	public String edit(@Valid User user, BindingResult result, RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			user.setRoles(new HashSet<Role>());
			model.addAttribute("roles", this.roleService.findAll());
			logger.warn("Erro na validação do formulário de edição do usuário: " + user.getUsername());
			return USER_FORM;
		}

		if (user.getRoleNames() == null || user.getRoleNames().isEmpty()) {
			user.setRoles(null);
		} else {
			Set<Role> newRoles = new HashSet<Role>();
			user.getRoleNames().forEach(rn -> {
				Role role = this.roleService.findAll().stream().filter(r -> rn.equals(r.getRoleName().toString())).findFirst().get();
				newRoles.add(role);
			});
			user.setRoles(newRoles);
		}

		Optional<User> optUser = this.userService.findByUsername(user.getUsername());
		User currentUser = optUser.get();
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEnabled(user.isEnabled());
		currentUser.setRoles(user.getRoles());
		this.userService.save(currentUser);

		attributes.addFlashAttribute("msgSuccess", "O usuário " + user.getUsername() + " foi editado com sucesso");

		logger.info("O usuário " + user.getUsername() + " foi editado com sucesso");

		return REDIRECT_USERS;
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("username") String username, RedirectAttributes attributes) {
		User user = this.userService.findByUsername(username).get();

		this.userService.delete(user);

		attributes.addFlashAttribute("msgSuccess", "O usuário " + user.getUsername() + " foi excluído com sucesso");

		logger.info("O usuário " + user.getUsername() + " foi excluído com sucesso");

		return REDIRECT_USERS;
	}
}
