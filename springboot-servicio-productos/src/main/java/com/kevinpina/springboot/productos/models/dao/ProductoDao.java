package com.kevinpina.springboot.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.kevinpina.springboot.productos.models.entity.Producto;

// CrudRepository es un @Component por ende ProductoDao es un @Component debido a la herencia
public interface ProductoDao extends CrudRepository<Producto, Long>{
	
}
