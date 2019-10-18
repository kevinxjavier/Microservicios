package com.kevinpina.springboot.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kevinpina.springboot.item.models.Item;
import com.kevinpina.springboot.item.models.Producto;

@Service
public class ItemServiceImpl implements ItemService {

	private final String ENDPOINT = "http://localhost:8001";

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

}
