package com.kevinpina.springboot.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient	// No es necesario, al agregar eureka-client al pom.xml se habilita
@RibbonClient(name = "servicio-productos")	// Tampoco es necesario cuando se habilita eureka-client en el pom.xml (El Balanceo lo provee ahora Eureka con Ribbon)
@EnableCircuitBreaker	// Habilitando Hystrix, para manejar posibles errores
@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
