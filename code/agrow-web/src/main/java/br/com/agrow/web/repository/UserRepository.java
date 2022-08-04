package br.com.agrow.web.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.agrow.web.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);

	@Query(value = "SELECT * FROM users"
			+ " WHERE enabled = :enabled"
			+ " AND email_validation = :emailValidation"
			+ " AND email_validation_key = :key",
			nativeQuery = true)
	Optional<User> findByEnabledAndEmailValidationAndEmailValidationKey(boolean enabled, boolean emailValidation, UUID key);
}
