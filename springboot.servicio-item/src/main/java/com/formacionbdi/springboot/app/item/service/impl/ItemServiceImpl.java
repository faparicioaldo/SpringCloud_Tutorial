package com.formacionbdi.springboot.app.item.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	RestTemplate clienteRest;
	
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));

		/*
		 * Se utiliza progrmacion funcional de java 8 para convertir un arreglo de productos a items
		 * */
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap(); 
		pathVariables.put("id",id.toString());
		Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class,pathVariables);
		return new Item(producto, cantidad);
	}

}
