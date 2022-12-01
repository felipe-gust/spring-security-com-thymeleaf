package com.thymeleaf.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuringWebConfig {
	
	/*especificando caminhos que serão autenticados */	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home", "/images/*").permitAll() /* acesso sem restrição */
				.anyRequest().authenticated() /* acesso liberado após login */
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()						
				)
				.logout((logout) -> logout.permitAll());
		
		return http.build();	
	}
	
	/* dados de acesso do usuário */
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("felipe")
				.password("123")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}

}
