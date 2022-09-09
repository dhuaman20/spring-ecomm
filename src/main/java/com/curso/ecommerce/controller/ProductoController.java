package com.curso.ecommerce.controller;


import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;

@Controller
@RequestMapping("/productos") // url del producto es la tabla
public class ProductoController {
	
		// crear trasas de lo que se esta realizando.
		private final Logger LOGGER= LoggerFactory.getLogger(ProductoController.class);
		
		@Autowired
		private ProductoService productoService;
		
		
		//creamos una variable subir imagenes en el servidor
		@Autowired
		private UploadFileService upload;
		
		
		
		// creamos un metodo que nos permita redireccion a la vista productos show
		@GetMapping("")
		public String show(Model model) {
			
			model.addAttribute("productos", productoService.findAll());
			
			return "productos/show";
		}
		
		//creamos un metodo que nos permita redireccionar a esa vista
		@GetMapping("/create")
		public String create() {
			return "productos/create";
		}
		
		// metodo para grabar producto
		@PostMapping("/save")
		public String save( Producto producto ,@RequestParam("img")  MultipartFile file) throws IOException {
			LOGGER.info("Este es el objeto producto {}",producto);
			
			Usuario u=new Usuario(1, "", "", "", "", "", "", "");
			producto.setUsuario(u);
			
			// subir la imagen en el servidor.
			if (producto.getId()==null) { // cuando se crea un producto
				String nombreImagen=upload.saveImage(file);
				// guardamos el nombre de la imagen
				producto.setImagen(nombreImagen);
				
			}else {
				
				if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagen
					Producto p=new Producto();
					p=productoService.get(producto.getId()).get();
					producto.setImagen(p.getImagen());
					
				}else {
					
					String nombreImagen=upload.saveImage(file);
					producto.setImagen(nombreImagen);
				}
				
			}
			
			productoService.save(producto);
			return "redirect:/productos";
		}
		
		
		//metodo para editar producto
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable  Integer id, Model model) {
			Producto producto=new Producto();
			Optional<Producto> optionalProducto=productoService.get(id);
			producto= optionalProducto.get();
			
			
			LOGGER.info("Producto Buscado: {}",producto);
			model.addAttribute("producto", producto);
			
			
			return "productos/edit";
		}
		
		
		//actualizar el producto
		@PostMapping("/update")
		public String update( Producto producto) {
			productoService.update(producto);
			
			return "redirect:/productos";
		}
		
		// crear un metodo para eliminar
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable Integer id) {
			productoService.delete(id);
			return "redirect:/productos";
		}
		
		
		
}
