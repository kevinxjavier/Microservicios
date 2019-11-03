package com.kevinpina.springboot.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Esta clase la creamos para manejar los errores o exitos de la autenticacion
 * se registra en SpringSecurityConfig.configure()
 */
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		log.info("Sucess: " + userDetails.getUsername());
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error("Error: " + exception.getMessage());
	}
	
}
