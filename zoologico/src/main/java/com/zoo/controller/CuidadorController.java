package com.zoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.models.AnimalModel;
import com.zoo.models.CuidadorModel;
import com.zoo.service.AnimalService;
import com.zoo.service.CuidadorService;

@Controller
public class CuidadorController {

	@Autowired
	private CuidadorService cuidadorService;

	@Autowired
	private AnimalService animalService;

//	@GetMapping("/todosCuidadores")
//	public String mostrarCuidadores(Model model) {
//	    List<CuidadorModel> lista = cuidadorService.listarCuidadores();
//	    model.addAttribute("cuidadores", lista);
//	    return "listaCuidadores";
//	}

	@GetMapping("/registrar")
	public String formularioRegistro(Model model) {
		model.addAttribute("cuidador", new CuidadorModel());
		return "user/formularioRegistro";
	}

	@PostMapping("/guardarCuidador")
	public String crearCuidador(@ModelAttribute CuidadorModel cuidador) {
		cuidadorService.addCuidador(cuidador);
		return "redirect:/user/index.html";
	}

	@GetMapping("/borrar/{id}")
	public String borrarCuidador(@PathVariable("id") Long id) {
		CuidadorModel cuidador = cuidadorService.getCuidadorById(id);
		List<AnimalModel> lista = animalService.listarAnimales();
		actualizarAnimales(animalService.listarAnimales(), cuidador);
		cuidadorService.deleteCuidador(cuidador);
		return "redirect:/user/index.html";
	}

	private void actualizarAnimales(List<AnimalModel> animales, CuidadorModel cuidador) {
		for (int i = 0; i < animales.size(); i++) {
			if (animales.get(i).getCuidador() == cuidador) {
				animales.get(i).setCuidador(null);
				animalService.addAnimal(animales.get(i));
			}
		}
	}

	@GetMapping("/editar/{id}")
	public String formulario(@PathVariable("id") Long id, Model model) {
		CuidadorModel cuidador = cuidadorService.getCuidadorById(id);
		model.addAttribute("cuidador", cuidador);
		return "/user/formularioActualizacion";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizarProducto(@PathVariable("id") Long id, @Validated @ModelAttribute CuidadorModel cuidador,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			cuidador.setIdCuidador(id);
			return "/user/formularioActualizacion";
		}
		CuidadorModel cuidadorActualizado = cuidadorService.getCuidadorById(id);
		cuidadorActualizado.setNombreCompleto(cuidador.getNombreCompleto());
		cuidadorActualizado.setDni(cuidador.getDni());
		cuidadorActualizado.setEdad(cuidador.getEdad());
		// OJO QUE CON LOS BOOLEANS LOS GETTERS CAMBIAN A ISACTIVO O IS GENERO
		cuidadorActualizado.setActivo(cuidador.isActivo());
		cuidadorActualizado.setGenero(cuidador.isGenero());
		cuidadorService.addCuidador(cuidadorActualizado);
		return "redirect:/user/index.html";
	}

}
