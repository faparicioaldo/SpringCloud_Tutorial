package com.formacionbdi.springboot.app.item.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;

/*
 * Primary and Qualifier are two alternative to do the same
 * diferentiate the two implementations of rest client 
 * */
@Service("serviceFeign")
//@Primary
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1, p.getPrecio() * 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Producto producto = clienteFeign.detalle(id);
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

	@Override
	public Item findByIdWithFallback(Long id, Integer cantidad) {
		Producto producto = clienteFeign.detalleFallback(id);
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

	@Override
	public Item findByIdWithTimeout(Long id, Integer cantidad) {
		Producto producto = clienteFeign.detalleTimeout(id);
		return new Item(producto, cantidad, producto.getPrecio() * cantidad);
	}

}
