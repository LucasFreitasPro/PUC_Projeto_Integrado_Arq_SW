package br.com.agrow.web.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@NotBlank(message = "O campo é obrigatório")
	@Email(message = "Deve ser um e-mail corretamente formatado")
	@Column(nullable = false, unique = true)
	private String username;

	@Size(min = 6, message = "A senha deve possuir um mínimo de 6 caracteres")
	@Column(nullable = false)
	private String password;

	@Size(min = 6, message = "A senha deve possuir um mínimo de 6 caracteres")
	@Transient
	private String repeatedPassword;

	@NotBlank(message = "O campo é obrigatório")
	@Column(nullable = false)
	private String firstName;

	@NotBlank(message = "O campo é obrigatório")
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private boolean enabled;

	@Column(nullable = false)
	private boolean emailValidation;

	@Column(nullable = true)
	private LocalDateTime expirationEmailValidation;

	@Column(nullable = true)
	private LocalDateTime emailValidationTime;

	@Column(nullable = true)
	private UUID emailValidationKey;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Transient
	private Set<String> roleNames;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getEmailValidation() {
		return emailValidation;
	}

	public void setEmailValidation(boolean emailValidation) {
		this.emailValidation = emailValidation;
	}

	public LocalDateTime getExpirationEmailValidation() {
		return expirationEmailValidation;
	}

	public void setExpirationEmailValidation(LocalDateTime expirationEmailValidation) {
		this.expirationEmailValidation = expirationEmailValidation;
	}

	public LocalDateTime getEmailValidationTime() {
		return emailValidationTime;
	}

	public void setEmailValidationTime(LocalDateTime emailValidationTime) {
		this.emailValidationTime = emailValidationTime;
	}

	public UUID getEmailValidationKey() {
		return emailValidationKey;
	}
	
	public void setEmailValidationKey(UUID emailValidationKey) {
		this.emailValidationKey = emailValidationKey;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
