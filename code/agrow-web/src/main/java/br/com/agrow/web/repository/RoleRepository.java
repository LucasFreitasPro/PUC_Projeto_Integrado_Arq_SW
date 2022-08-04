package br.com.agrow.web.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.agrow.web.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	public Optional<Role> findByRoleName(String roleName);

	@Query(value = "SELECT COUNT(*) > 0 FROM users_roles WHERE role_id = :roleId", nativeQuery = true)
	public boolean isRoleInUse(UUID roleId);
}
