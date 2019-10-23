package com.kevinpina.springboot.item.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kevinpina.springboot.item.models.Item;
import com.kevinpina.springboot.item.models.Producto;
import com.kevinpina.springboot.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")
//	@Qualifier("itemServiceImpl")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	/*
	 * Se invoca cuando no se encuentra un producto, esto da un NullPointerException en servicio-productos
	 * 
	 * Tambien se invoca cuando el metodo en servicio-productos tarda 2 Seg, dando un TimeOut aca, ya que 
	 * Ribbon con Feign y Hystrix tienen un tiempo de respuesta de 1 Segundo.
	 * A menos que se aumente el tiempo en el application.properties y asi este metodo no se ejecutaria,
	 * y seguiria el flujo normal ejecutandose solo detalle() ya que se esperaria la respuesta.
	 */
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setFechaCreacion(new Date());
		producto.setId(id);
		producto.setNombre("Default Name");
		producto.setPort(36);
		producto.setPrecio(1000D);
		item.setProducto(producto);
		
		return item;
	}
}
