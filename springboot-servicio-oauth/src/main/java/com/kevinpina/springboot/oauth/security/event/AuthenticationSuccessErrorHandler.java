package com.kevinpina.springboot.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.kevinpina.springboot.commons.usuarios.models.entity.Usuario;
import com.kevinpina.springboot.oauth.services.IUsuarioService;

import brave.Tracer;
import feign.FeignException;

/**
 * Esta clase la creamos para manejar los errores o exitos de la autenticacion
 * se registra en SpringSecurityConfig.configure()
 */
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	// Lo usamos para agregar un Tag a las traza de Sleuth Zipkin
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		log.info("Sucess: " + userDetails.getUsername());
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String errorMessage = "Error: " + exception.getMessage();
		log.error(errorMessage);
		
		// Se usa try-catch porque pudiera no existir usuario lo que nos devolveria un codigo 404
		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(errorMessage);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			
			log.info("Intento actual es de:" + usuario.getIntentos());
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intentos despues es de:" + usuario.getIntentos());
			
			errors.append(" - Intentos de login:" + usuario.getIntentos());
			
			if(usuario.getIntentos() >= 3) {
				String errorMaximosIntentos = String.format("Usuario %s deshabilitado por maximos intentos.", usuario.getUsername());
				
				log.error(errorMaximosIntentos);
				errors.append(" - " + errorMaximosIntentos);
				
				usuario.setEnabled(false);
			}
			
			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
			usuarioService.update(usuario, usuario.getId());
		} catch(FeignException e) {
			log.error(String.format("Usuario %s no existe en el sistema", authentication.getName()));
		}		
	}
	
}
