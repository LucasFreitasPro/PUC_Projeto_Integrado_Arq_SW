package br.com.agrow.web.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.agrow.web.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final LoginSuccessHandler loginSuccessHandler;
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public WebSecurityConfig(PasswordEncoder passwordEncoder, LoginSuccessHandler loginSuccessHandler, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.passwordEncoder = passwordEncoder;
		this.loginSuccessHandler = loginSuccessHandler;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/home", "/users/prepare-to-register", "/users/register", "/email/validation-result").permitAll()
			.antMatchers("/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**").permitAll()
			.antMatchers(HttpMethod.GET, "/email-validation/**").permitAll()
			.antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/roles/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().successHandler(loginSuccessHandler)
			.loginPage("/login").permitAll()
			.and()
			.logout().permitAll();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}
}
