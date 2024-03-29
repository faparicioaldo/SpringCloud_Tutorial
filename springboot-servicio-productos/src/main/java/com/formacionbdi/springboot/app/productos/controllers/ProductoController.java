package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto -> {
//			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);

		boolean ok = false;
		if(!ok) {
//			throw new RuntimeException("No se pudo cargar el producto");
		}
				
		return producto;
	}

	@GetMapping("/ver/with/fallback/{id}")
	public Producto detalleFallback(@PathVariable Long id){
		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);

		boolean ok = false;
		if(!ok) {
			throw new RuntimeException("No se pudo cargar el producto");
		}
		
		return producto;
	}

	@GetMapping("/ver/with/timeout/{id}")
	public Producto detalleTimeout(@PathVariable Long id){
		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		
		try {
			Thread.sleep(30000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return producto;
	}
}
