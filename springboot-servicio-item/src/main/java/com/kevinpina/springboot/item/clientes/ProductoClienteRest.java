package com.kevinpina.springboot.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kevinpina.springboot.item.models.Producto;

/**
 * Declarando que es un cliente Feign y el name="servicio-productos" es el 
 * nombre del servicio que vamos a consumr, en este caso de springboot-servicio-productos/
 */
@FeignClient(name = "servicio-productos")	// Ejecutar y ver de springboot-servicio-productos/ ver application.properties	
public interface ProductoClienteRest {

	// Estos metodos se implementan en tiempo de ejecucion y son del proyecto springboot-servicio-productos/
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

}
