package com.kevinpina.springboot.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kevinpina.springboot.productos.models.entity.Producto;
import com.kevinpina.springboot.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(p -> { 
			p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		
		producto.setPort(port);	// Esto dara una NullPointerException, cuando no haya producto 101
		
		try {
			// El proyecto servicio-items Ribbon con Feign y Hystrix tienen un tiempo de respuesta de 1 Segundo. 
			// Este sleep de 2 seg dara un Internal Server Error: TimeOut en servicio-items
			Thread.sleep(2000);	 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return producto;
	}
	
}
