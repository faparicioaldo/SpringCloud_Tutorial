package com.fomacionbdi.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fomacionbdi.springboot.app.productos.models.entity.Producto;

/*
 * NO es necesario anotar esta clase con algun esteriotipo de Spring (Repository, Service, Component)
 * Por que al heredar de CrudRepository la creacion del BEAN la hace Spring por debajo. 
 * */
public interface ProductoDao extends CrudRepository<Producto, Long>{

}
