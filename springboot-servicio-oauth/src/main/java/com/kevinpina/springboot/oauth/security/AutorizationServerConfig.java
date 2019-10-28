package com.kevinpina.springboot.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AutorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired	// Agregado al Contenedor de Beans desde la clase SpringSecurityConfig con la anotacion @Bean
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired	// Agregado al Contenedor de Beans desde la clase SpringSecurityConfig con la anotacion @Bean
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")		// Es el endpoint para autenticar el token [POST] /oauth/token
			.checkTokenAccess("isAuthenticated()");	// Este endpoint se encarga de validar el token. 
													// Ambos endpoint estan protegidos con Autenticacion HTTP Basic
														// Header Authorization Basic: clientid y clientsecret 
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("frontendapp")
			.secret(passwordEncoder.encode("123456"))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token") // refresh_token  = Actualiza el token antes de caducar
			.accessTokenValiditySeconds(3600)	// Validez del token 1 Hora
			.refreshTokenValiditySeconds(3600)			
//			.and()								// Asi colocamos mas usuarios de aplicacion
//			.withClient("androidapp")
//			.secret(passwordEncoder.encode("123456"))
//			.scopes("read", "write")
//			.authorizedGrantTypes("password", "refresh_token") // refresh_token  = Actualiza el token antes de caducar
//			.accessTokenValiditySeconds(3600)	// Validez del token 1 Hora
//			.refreshTokenValiditySeconds(3600)
			; 
	}

	@Override	// Endpoint [POST] /oauth/token
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public JwtTokenStore tokenStore() {		
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey("MI_CLAVE_SECRETA");
		return token;
	}
	
	
}
