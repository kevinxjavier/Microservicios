package com.kevinpina.springboot.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer	// Habilitando la configuracion del Servidor de Recursos
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**")	// Aplica la autenticacion a cualquier metodo POST, GET ...
		.permitAll()	// De esta forma hacemos publica la ruta cualquier usuario puede iniciar session
		
		.antMatchers(HttpMethod.GET, "/api/productos/listar", "/api/items/listar", "/api/usuarios/usuariocrud").permitAll()	// Haciendo estas rutas publicas 
		
		.antMatchers(HttpMethod.GET, "/api/productos/ver/{id}", "/api/items/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuariocrud/{id}")
			.hasAnyRole("ADMIN", "USER")	// Para los Roles no se coloca el prefijo ROLE_ ya que se inserta automaticamente
			
//		.antMatchers(HttpMethod.POST, "/api/productos/crear", "/api/items/crear", "/api/usuarios/usuariocrud").hasRole("ADMIN")	// solo los usuarios ROL_ADMIN pueden crear
		
//		.antMatchers(HttpMethod.PUT, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuariocrud/{id}").hasRole("ADMIN")	// solo los usuarios ROL_ADMIN pueden crear
		
//		.antMatchers(HttpMethod.DELETE, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuariocrud/{id}").hasRole("ADMIN");	// solo los usuarios ROL_ADMIN pueden crear
			
		.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")	// Esta linea resume las 3 comentadas arriba
		
		.anyRequest().authenticated(); // Esta linea define que las rutas genericas o que no se hayan configurado arriba requieren autenticacion.
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
