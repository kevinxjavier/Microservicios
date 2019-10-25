package com.kevinpina.springboot.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kevinpina.springboot.item.models.Item;
import com.kevinpina.springboot.commons.models.entity.Producto;

@Service
public class ItemServiceImpl implements ItemService {

	//private final String ENDPOINT = "http://localhost:8001";
	private final String ENDPOINT = "http://servicio-productos";

	@Autowired
	private RestTemplate clienteRest;

	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject(ENDPOINT + "/listar", Producto[].class));
		
		/*List<Item> items = new ArrayList<Item>();
		for(Iterator<Producto> p = productos.iterator(); p.hasNext() ;) 
			items.add(new Item(p.next(), 1));
		return items; */	
		
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		Producto producto = clienteRest.getForObject(ENDPOINT + "/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> bodyRequest = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange(ENDPOINT + "/crear", HttpMethod.POST, bodyRequest, Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> bodyRequest = new HttpEntity<Producto>(producto);
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		ResponseEntity<Producto> response = clienteRest.exchange(ENDPOINT + "/editar/{id}", HttpMethod.PUT, bodyRequest, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		clienteRest.delete(ENDPOINT + "/eliminar/{id}", pathVariables);		
	}

}
