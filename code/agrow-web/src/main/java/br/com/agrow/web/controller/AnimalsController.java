package br.com.agrow.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agrow.web.lib.dto.UnidadeAnimalRegisteringRequest;
import br.com.agrow.web.model.User;

@Controller
@RequestMapping("/animals")
public class AnimalsController {

	private final String ANIMAL_LIST = "animal/list";
	private final String ANIMAL_FORM = "animal/form";
	private final String REDIRECT_ANIMALS = "redirect:/animals";

	@GetMapping
	public String index(Model model) {
		model.addAttribute("unidadesAnimais", new ArrayList<>());
		return ANIMAL_LIST;
	}

	@GetMapping("/prepare-to-create")
	public String prepareToCreate(Model model) {
		model.addAttribute("unidadeAnimal", new UnidadeAnimalRegisteringRequest());
		return ANIMAL_FORM;
	}

	@PostMapping("/create")
	public String create(User user, RedirectAttributes attributes) {
		user.setExpirationEmailValidation(LocalDateTime.now().plusHours(1));
		user.setEmailValidationKey(UUID.randomUUID());

		attributes.addFlashAttribute("registered", true);

		return REDIRECT_ANIMALS;
	}
}
