package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;

//@FeignClient(name = "servicio-productos", url="localhost:8001")
//@FeignClient(name = "servicio-productos") // Se quita dominio por ribbon configuration
@FeignClient(name = "SERVICIO-PRODUCTOS") // Se quita dominio por ribbon configuration
public interface ProductoClienteRest {

	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

	@GetMapping("/ver/with/fallback/{id}")
	public Producto detalleFallback(@PathVariable Long id);

	@GetMapping("/ver/with/timeout/{id}")
	public Producto detalleTimeout(@PathVariable Long id);

}
