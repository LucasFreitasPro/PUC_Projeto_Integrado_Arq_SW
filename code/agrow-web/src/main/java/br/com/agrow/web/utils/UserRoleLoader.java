package br.com.agrow.web.utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.agrow.web.model.Role;
import br.com.agrow.web.model.User;
import br.com.agrow.web.service.RoleService;
import br.com.agrow.web.service.UserService;

@Component
public class UserRoleLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final RoleService roleService;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserRoleLoader(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
		this.roleService = roleService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Role> roles = this.roleService.findAll();
		if (roles.isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setRoleName("ADMIN");
			this.roleService.save(roleAdmin);
			this.logger.info("Função inserida com sucesso: " + roleAdmin);

			Role roleUser = new Role();
			roleUser.setRoleName("USER");
			this.roleService.save(roleUser);
			this.logger.info("Função inserida com sucesso: " + roleUser);

			Role role3 = new Role();
			role3.setRoleName("TECHNICIAN");
			this.roleService.save(role3);
			this.logger.info("Função inserida com sucesso: " + role3);

			Role role4 = new Role();
			role4.setRoleName("ENGINEER");
			this.roleService.save(role4);
			this.logger.info("Função inserida com sucesso: " + role4);
		} else {
			logger.info(roles.size() + " Roles já estão cadastradas");
		}
		Optional<User> optional = this.userService.findByUsername("admin@agrow.com.br");
		if (!optional.isPresent()) {
			User admin = new User();
			admin.setEnabled(true);
			admin.setFirstName("admin");
			admin.setLastName("agrow");
			admin.setUsername("admin@agrow.com.br");
			admin.setRoles(Set.of(this.roleService.findByRoleName("ADMIN").get()));
			admin.setPassword(this.passwordEncoder.encode("123"));
			admin.setRepeatedPassword(admin.getPassword());
			this.userService.save(admin);
			logger.info("Usuário admin inserido com sucesso");
		} else {
			logger.info("Usuário admin já está cadastrado");
		}
	}
}
