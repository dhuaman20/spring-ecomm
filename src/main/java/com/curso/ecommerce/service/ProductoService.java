package com.curso.ecommerce.service;

import java.util.Optional;

import com.curso.ecommerce.model.Producto;

public interface ProductoService {

	public Producto save(Producto producto);
	public Optional<Producto> get (Integer id); // para validar si existe o no de la base de datos
	public void update(Producto producto);
	public void delete(Integer id);
	
	
}
