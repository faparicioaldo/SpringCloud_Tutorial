package com.formacionbdi.springboot.app.item.models.service.impl;

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
import com.formacionbdi.springboot.app.item.models.service.ItemService;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;
	
//	private String domain = "http://localhost:8001";
//	private String domain = "http://servicio-productos";//Para Ribbon
	private String domain = "http://SERVICIO-PRODUCTOS";//Para Ribbon
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject(domain + "/listar", Producto[].class));
				
		return productos.stream().map(p -> new Item(p, 1, p.getPrecio() * 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject(domain + "/ver/{id}", Producto.class, pathVariables);		
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

	@Override
	public Item findByIdWithFallback(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject(domain + "/ver/with/fallback/{id}", Producto.class, pathVariables);		
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

	@Override
	public Item findByIdWithTimeout(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject(domain + "/ver/with/timeout/{id}", Producto.class, pathVariables);		
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

}
