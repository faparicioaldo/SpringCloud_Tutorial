package com.fomacionbdi.springboot.app.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fomacionbdi.springboot.app.productos.models.entity.Producto;
import com.fomacionbdi.springboot.app.productos.models.service.IProductoService;

/*
 * Esta anotacion a diferencia de @Controller se usa para convertir el cuerpo de la respuesta en JSON o XML
 * con el uso de ResponseBody
 * */
@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	/*
	 * Esta anotacion define un endpoint desde donde se podra consultar a este servicio
	 * Mapea a un endpointy (ruta URL) al metodo controller listar() 
	 * */
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		return productoService.findById(id);
	}

}
