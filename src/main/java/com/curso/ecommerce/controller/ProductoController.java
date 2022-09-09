package com.curso.ecommerce.controller;


import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/productos") // url del producto es la tabla
public class ProductoController {
	
		// crear trasas de lo que se esta realizando.
		private final Logger LOGGER= LoggerFactory.getLogger(ProductoController.class);
		
		@Autowired
		private ProductoService productoService;
			
		
		// creamos un metodo que nos permita redireccion a la vista productos show
		@GetMapping("")
		public String show() {
			return "productos/show";
		}
		
		//creamos un metodo que nos permita redireccionar a esa vista
		@GetMapping("/create")
		public String create() {
			return "productos/create";
		}
		
		// metodo para grabar producto
		@PostMapping("/save")
		public String save( Producto producto) {
			LOGGER.info("Este es el objeto producto {}",producto);
			
			Usuario u=new Usuario(1, "", "", "", "", "", "", "");
			producto.setUsuario(u);
			
			
			productoService.save(producto);
			return "redirect:/productos";
		}
		
		
}
