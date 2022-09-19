package br.com.agrow.web.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrow.web.model.User;
import br.com.agrow.web.repository.UserRepository;

@Service
@Transactional
public class UserService {

	private final RoleService roleService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(RoleService roleService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.roleService = roleService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public Optional<User> findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	public Optional<User> findByEnabledAndEmailValidationAndEmailValidationKey(boolean enabled, boolean emailValidation, UUID key) {
		return this.userRepository.findByEnabledAndEmailValidationAndEmailValidationKey(enabled, emailValidation, key);
	}

	public User register(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setEnabled(Boolean.FALSE);
		user.setEmailValidation(Boolean.FALSE);
		user.setRoles(Set.of(this.roleService.findByRoleName("ADMIN").get()));
		return save(user);
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public void delete(User user) {
		this.userRepository.delete(user);
	}
}
