package com.kevinpina.springboot.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration	
public class AppConfig {
	
	/** 
	 * Creando un componente @Bean y registrandolo en el Contendor con el nombre registrarRestTemplate 
	 * pero se cambio por clienteRest al colocar @Bean("clienteRest").
	 * 
	 * RestTemplate.class devuelve un HttpClient para consumir recursos de otros microservicios. 
	 */
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
}
