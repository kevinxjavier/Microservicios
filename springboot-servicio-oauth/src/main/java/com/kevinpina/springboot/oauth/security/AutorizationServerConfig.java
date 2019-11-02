package com.kevinpina.springboot.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope			// Colocamos esta anotacion para que funcione la URL de refrescamiento de parametros de Spring Cloud Config
@Configuration
@EnableAuthorizationServer
public class AutorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private Environment env;

	@Autowired	// Agregado al Contenedor de Beans desde la clase SpringSecurityConfig con la anotacion @Bean
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired	// Agregado al Contenedor de Beans desde la clase SpringSecurityConfig con la anotacion @Bean
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// security es para dar seguridad a los tokens
		security.tokenKeyAccess("permitAll()")					// Es el endpoint para autenticar el token [POST] /oauth/token con los username y password del ClienteApp y del Usuario
			.checkTokenAccess("isAuthenticated()");				// Este endpoint se encarga de validar el token. 
																// Ambos endpoint estan protegidos con Autenticacion HTTP Basic
																	// Header Authorization Basic: clientid y clientsecret 
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(env.getProperty("config.security.oauth.cliente.id"))				// ClientID o ClienteApp		= "frontendapp"
			.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.cliente.secret")))	// PasswordApp o ClientSecret	= "12345" 
			.scopes("read", "write")							// La aplicacion puede leer y escribir
			.authorizedGrantTypes("password", "refresh_token")	// password = "Tipo de autenticacion para obtener el token a traves de username y password tanto del ClienteApp como del Usuario" | refresh_token  = Actualiza el token antes de caducar
			.accessTokenValiditySeconds(3600)					// Validez del token 1 Hora
			.refreshTokenValiditySeconds(3600)			
//			.and()												// Asi colocamos mas usuarios de aplicacion
//			.withClient("androidapp")
//			.secret(passwordEncoder.encode("123456"))
//			.scopes("read", "write")
//			.authorizedGrantTypes("password", "refresh_token")	// refresh_token  = Actualiza el token antes de caducar
//			.accessTokenValiditySeconds(3600)					// Validez del token 1 Hora
//			.refreshTokenValiditySeconds(3600)
			; 
	}

	@Override	// Endpoint [POST] /oauth/token
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));	// Agregamos claims nuestros infoAdicionalToken con los de accessTokenConverter() 
																											// es la informacion por defecto "username, role, fechaExpiracion"
		
		endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())	// accessTokenConverter() es la informacion por defecto "username, role, fechaExpiracion"
			.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {		
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));	//"MI_CLAVE_SECRETA"
		return token;
	}
	
}
