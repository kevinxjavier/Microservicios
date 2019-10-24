package com.kevinpina.springboot.productos.models.service;

import java.util.List;

import com.kevinpina.springboot.productos.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();	
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
	
}
