package com.kevinpina.springboot.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService;
	
	@Autowired
	private AuthenticationEventPublisher AuthenticationSuccessErrorHandler;

	@Override
	@Autowired	// Inyectando AuthenticationManagerBuilder auth
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder())	// Codificando la contraseña;
			
			.and().authenticationEventPublisher(AuthenticationSuccessErrorHandler);	// Comentar sino se quiere manejar Success o Errors en la autenticacion
	}
	
	@Bean	// Agregamos al Contenedor de Spring, lo mismo que @Component pero este es a nivel de metodos, Ej: @Autowired private Clase nombreMetodo;
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean	// Agregamos al Contenedor de Spring, lo mismo que @Component pero este es a nivel de metodos, Ej: @Autowired private Clase nombreMetodo;
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
		
}
