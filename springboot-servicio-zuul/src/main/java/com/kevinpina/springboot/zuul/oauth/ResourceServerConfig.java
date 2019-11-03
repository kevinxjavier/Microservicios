package com.kevinpina.springboot.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope			// Colocamos esta anotacion para que funcione la URL de refrescamiento de parametros de Spring Cloud Config
@Configuration
@EnableResourceServer	// Habilitando la configuracion del Servidor de Recursos
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**")	// Aplica la autenticacion a cualquier metodo POST, GET ...
			.permitAll()	// De esta forma hacemos publica la ruta cualquier usuario puede iniciar session
		
		.antMatchers(HttpMethod.GET, "/api/productos/listar", "/api/items/listar", "/api/usuarios/usuariocrud")
			.permitAll()	// Haciendo estas rutas publicas, no se necesitan ROLES_ 
		
		.antMatchers(HttpMethod.GET, "/api/productos/ver/{id}", "/api/items/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuariocrud/{id}")
			.hasAnyRole("ADMIN", "USER")	// Para los Roles no se coloca el prefijo ROLE_ ya que se inserta automaticamente
			
//		.antMatchers(HttpMethod.POST, "/api/productos/crear", "/api/items/crear", "/api/usuarios/usuariocrud").hasRole("ADMIN")	// solo los usuarios ROL_ADMIN pueden crear
		
//		.antMatchers(HttpMethod.PUT, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuariocrud/{id}").hasRole("ADMIN")	// solo los usuarios ROL_ADMIN pueden crear
		
//		.antMatchers(HttpMethod.DELETE, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuariocrud/{id}").hasRole("ADMIN");	// solo los usuarios ROL_ADMIN pueden crear
			
		.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")	// Esta linea resume las 3 comentadas arriba
		
		.anyRequest().authenticated() // Esta linea define que las rutas genericas o que no se hayan configurado arriba requieren autenticacion.
		
		.and().cors().configurationSource(corsConfigurationSource());	// Habilitando Cors, sino se quiere comentar esta linea
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");	// Podemos especifcar el dominio "http://kevin.gs" ó "*" para que sea accedido por cualquiera
		//corsConfiguration.setAllowedOrigins(Arrays.asList("http://kevin.gs", "http://kevinpina.com"));	// Agregando varios Origins simultaneamente
		corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);	// "/**" con eso se aplica la configuracion a todas las rutas
		return source;
	}
	
	/**
	 * Bean para registar un filtro de Cors, asi no solo quede registrado en Spring Security sino que tambien quede
	 * configurado a nivel global en un filtro de spring para toda la aplicacion en general 
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public JwtTokenStore tokenStore() {		
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey(jwtKey);
		return token;
	}

}
