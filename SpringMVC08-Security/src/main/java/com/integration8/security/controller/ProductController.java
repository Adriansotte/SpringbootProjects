package com.integration8.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.integration8.security.model.Product;
import com.integration8.security.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/registrar")
	public String formularioRegistro(Model model) {
		model.addAttribute("producto", new Product());
		return "user/formularioRegistro";
	}
	
	@PostMapping("/guardarProducto")
	public String registrarPersona(@ModelAttribute Product nuevoProducto) {
		productService.addProduct(nuevoProducto);
		return "redirect:/user/index.html";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarProducto(@PathVariable("id") Long id) {
		Product producto = productService.getProductById(id);
		productService.deleteProduct(producto);
		return "redirect:/user/index.html";
	}
	
	@GetMapping("/editar/{id}")
	public String formulario(@PathVariable("id") Long id, Model model) {
		Product producto = productService.getProductById(id);
		model.addAttribute("producto", producto);
		return "/user/formularioActualizacion";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarProducto(@PathVariable("id") Long id, @Validated @ModelAttribute Product producto,
	        BindingResult result, Model model) {
	    if(result.hasErrors()) {
	        producto.setId(id);
	        return "/user/formularioActualizacion";
	    }
	    Product productoActualizado = productService.getProductById(id);
	    productoActualizado.setDescripción(producto.getDescripción());
	    productoActualizado.setFechaCaducidad(producto.getFechaCaducidad());
	    productoActualizado.setNombre(producto.getNombre());
	    productoActualizado.setPeso(producto.getPeso());
	    productoActualizado.setPrecio(producto.getPrecio());
	    productoActualizado.setStock(producto.getStock());
	    productService.addProduct(productoActualizado); // Asegúrate de llamar al método para actualizar el producto en tu servicio
	    return "redirect:/user/index.html"; // Redirige a la página que muestra la lista de productos
	}

}
