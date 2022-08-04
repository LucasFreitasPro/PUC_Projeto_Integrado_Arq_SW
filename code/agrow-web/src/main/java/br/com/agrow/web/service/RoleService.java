package br.com.agrow.web.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.agrow.web.model.Role;
import br.com.agrow.web.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAll() {
		return this.roleRepository.findAll();
	}

	public Optional<Role> findByRoleName(String roleName) {
		return this.roleRepository.findByRoleName(roleName);
	}

	public Role save(Role role) {
		return this.roleRepository.save(role);
	}

	public void delete(Role role) {
		this.roleRepository.delete(role);
	}

	public boolean isRoleInUse(UUID roleId) {
		return this.roleRepository.isRoleInUse(roleId);
	}
}
