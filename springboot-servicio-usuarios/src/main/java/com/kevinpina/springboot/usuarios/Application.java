package com.kevinpina.springboot.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.kevinpina.springboot.commons.usuarios.models.entity"})	// Como el proyecto springboot-servicio-commons-usuarios tiene clases de tipo @Component
																				// pero en diferentes paquetes que el por defecto de esta clase, lo agregamos al Scan
																				// de Spring para que se carguen como Beans al Contenedor de Spring.
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
