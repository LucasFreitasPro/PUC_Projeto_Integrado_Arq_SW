package br.com.agrow.web.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.agrow.web.model.Role;
import br.com.agrow.web.service.RoleService;

@Component
public class RoleLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final RoleService roleService;

	public RoleLoader(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Role> all = this.roleService.findAll();
		if (all.isEmpty()) {
			Role role1 = new Role();
			role1.setRoleName("ADMIN");
			this.roleService.save(role1);
			this.logger.info("Função inserida com sucesso: " + role1);

			Role role2 = new Role();
			role2.setRoleName("USER");
			this.roleService.save(role2);
			this.logger.info("Função inserida com sucesso: " + role2);

			Role role3 = new Role();
			role3.setRoleName("TECHNICIAN");
			this.roleService.save(role3);
			this.logger.info("Função inserida com sucesso: " + role3);

			Role role4 = new Role();
			role4.setRoleName("ENGINEER");
			this.roleService.save(role4);
			this.logger.info("Função inserida com sucesso: " + role4);
		}
	}
}
