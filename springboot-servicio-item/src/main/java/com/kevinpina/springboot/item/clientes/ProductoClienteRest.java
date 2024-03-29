package com.kevinpina.springboot.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kevinpina.springboot.commons.models.entity.Producto;

/**
 * Declarando que es un cliente Feign y el name="servicio-productos" es el
 * nombre del servicio que vamos a consumr, en este caso de
 * springboot-servicio-productos/
 */
@FeignClient(name = "servicio-productos") // Ejecutar y ver de springboot-servicio-productos/ ver application.properties
public interface ProductoClienteRest {

	// Estos metodos se implementan en tiempo de ejecucion y son del proyecto
	// springboot-servicio-productos/

	@GetMapping("/listar")
	public List<Producto> listar();

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);

	@PutMapping("/editar/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id);

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);

}
