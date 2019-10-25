package com.kevinpina.springboot.item.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kevinpina.springboot.item.models.Item;
import com.kevinpina.springboot.commons.models.entity.Producto;
import com.kevinpina.springboot.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope // Los @Components son singleton, con esta anotacion hacemos que se refresque la
				// instancia al invocar una URL dada
@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")
//	@Qualifier("itemServiceImpl")
	private ItemService itemService;

	@Autowired
	private Environment env;

	@Value("${configuracion.texto}")
	private String texto;

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
	 * Se invoca cuando no se encuentra un producto, esto da un NullPointerException
	 * en servicio-productos
	 * 
	 * Tambien se invoca cuando el metodo en servicio-productos tarda 2 Seg, dando
	 * un TimeOut aca, ya que Ribbon con Feign y Hystrix tienen un tiempo de
	 * respuesta de 1 Segundo. A menos que se aumente el tiempo en el
	 * application.properties y asi este metodo no se ejecutaria, y seguiria el
	 * flujo normal ejecutandose solo detalle() ya que se esperaria la respuesta.
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

	@GetMapping("obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port) {
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("port", port);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) { // el indice [0] es el
																								// nombre del profile
																								// activo
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	@PostMapping("/crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.update(producto, id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}

}
