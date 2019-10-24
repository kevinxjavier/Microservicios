package com.kevinpina.springboot.item.models.service;

import java.util.List;

import com.kevinpina.springboot.item.models.Item;
import com.kevinpina.springboot.item.models.Producto;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

	public Producto save(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);

}
