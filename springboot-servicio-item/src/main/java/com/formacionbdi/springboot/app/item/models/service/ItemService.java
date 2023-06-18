package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	public Item findByIdWithFallback(Long id, Integer cantidad);
	public Item findByIdWithTimeout(Long id, Integer cantidad);
}
