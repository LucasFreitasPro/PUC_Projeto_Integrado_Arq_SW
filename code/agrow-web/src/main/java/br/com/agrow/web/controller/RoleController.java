package br.com.agrow.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agrow.web.model.Role;
import br.com.agrow.web.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final String ROLE_LIST = "/role/list";
	private final String ROLE_FORM = "/role/form";
	private final String REDIRECT_ROLES = "redirect:/roles/";

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("roles", this.roleService.findAll());
		return ROLE_LIST;
	}

	@GetMapping("/prepare-to-edit")
	public String prepareToEdit(@RequestParam("roleName") String roleName, Model model) {
		Role role = this.roleService.findByRoleName(roleName).get();

		model.addAttribute("role", role);

		return ROLE_FORM;
	}

	@GetMapping("/prepare-to-save")
	public String prepareToSave(Model model) {
		model.addAttribute("role", new Role());

		return ROLE_FORM;
	}

	@PostMapping("/save")
	public String save(@Valid Role role, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			logger.warn("Erro na validação do formulário de cadastro de função");
			return ROLE_FORM;
		}
		role.setRoleName(role.getRoleName().toUpperCase());
		if (this.roleService.findByRoleName(role.getRoleName()).isPresent()) {
			result.addError(new FieldError("role", "roleName", "O nome informado já está sendo utilizado"));
			logger.warn("O nome informado " + role.getRoleName() + " já está sendo utilizado");
			return ROLE_FORM;
		}

		String msgSuccess = "A função " + role.getRoleName() + " foi editada com sucesso";
		if (role.getRoleId() != null) {
			msgSuccess = "A função " + role.getRoleName() + " foi cadastrada com sucesso";
		}

		this.roleService.save(role);

		attributes.addFlashAttribute("msgSuccess", msgSuccess);

		logger.info(msgSuccess);

		return REDIRECT_ROLES;
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("roleName") String roleName, RedirectAttributes attributes) {
		Optional<Role> optional = this.roleService.findByRoleName(roleName);

		boolean isRoleInUse = this.roleService.isRoleInUse(optional.get().getRoleId());
		if (isRoleInUse) {
			attributes.addFlashAttribute("msgError", "A função " + roleName + " está sendo utilizada, portando não pode ser excluída");
			logger.warn("A função " + roleName + " está sendo utilizada, portando não pode ser excluída");
		} else {
			this.roleService.delete(optional.get());
			attributes.addFlashAttribute("msgSuccess", "A função " + roleName + " foi excluída com sucesso");
			logger.info("A função " + roleName + " foi excluída com sucesso");
		}
		return REDIRECT_ROLES;
	}
}
