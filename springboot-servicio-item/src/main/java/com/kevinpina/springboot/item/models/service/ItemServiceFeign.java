package com.kevinpina.springboot.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kevinpina.springboot.item.clientes.ProductoClienteRest;
import com.kevinpina.springboot.item.models.Item;

/**
 * Forma 1:
 * Al haber 2 @Services que implementan ItemService con esta anotacion @Primary
 * le decimos a Spring que inyecte este Bean (En el @Controller en este caso). 
 * 
 * Forma 2:
 * Otra alternativa es usar @Qualifier("itemServiceFeign) en el @Autowired y  
 * el nombre de la clase a injectar empezaria en minuscula. O si el @Service 
 * tiene nombre; se usa dicho nombre en lugar del nombre de clase emepezando en minuscula:
 *	@Service("aService")
 *	class A {}  
 *   
 *  class B {  
 *    @Autowird
 *    @Qualifier("aService")
 *    private ...
 *  }
 *
 *  En esta app usamos las dos formas, con usar una basta.
 */
@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest productoClienteRest;
	
	@Override
	public List<Item> findAll() {
		return productoClienteRest.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoClienteRest.detalle(id), cantidad);
	}

}
