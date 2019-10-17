package com.kevinpina.springboot.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kevinpina.springboot.productos.models.entity.Producto;
import com.kevinpina.springboot.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll();
	}
	
	@GetMapping("listar/{id}")
	public Producto detalle(@PathVariable Long id) {
		return productoService.findById(id);
	}
	
}
