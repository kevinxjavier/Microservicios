package com.kevinpina.springboot.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // No es necesario, al agregar eureka-client al pom.xml se habilita
@SpringBootApplication
@EntityScan({ "com.kevinpina.springboot.commons.models.entity" }) // Scaneamos los Beans @Entity, @Repository,
																	// @Controller de este paquete que esta en otra
																	// libreria
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
