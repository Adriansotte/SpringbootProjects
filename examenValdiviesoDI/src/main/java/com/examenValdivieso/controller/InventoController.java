package com.examenValdivieso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.cache.annotation.CacheEvict;
import com.examenValdivieso.model.InventoModel;
import com.examenValdivieso.model.PatenteModel;
import com.examenValdivieso.service.InventoService;
import com.examenValdivieso.service.PatenteService;

//En esta clase como no son rest controllers los he separado y he añadido aqui lo del tema del cache,
// ya que es donde se lista

@Controller
public class InventoController {

	@Autowired
	private InventoService inventoService;
	
	@Autowired
	private PatenteService patenteService;
	
	// En esta parte solo utilizo el cache para guardar informacion de los inventos
	// no para guardar la informacion de las patentes
	
	@GetMapping("/registrar")
	@CacheEvict(value="invento", allEntries = true)
	public String formularioRegistro(Model model) {
		model.addAttribute("invento", new InventoModel());		
		return "user/formularioRegistro";
	}
	
	@PostMapping("/guardarInvento")
	@CacheEvict(value="invento", allEntries = true)
	public String registrarInvento(@ModelAttribute InventoModel invento) {
		inventoService.addInveto(invento);
		return "redirect:/user/index.html";
	}
	
	//Estos son dos controladores que van a parte pero como pertenecen a un controlador normal
	// los he juntado. SIN CACHE
	
	@GetMapping("/registrarPatente")
	public String formularioRegistroPatente(Model model) {
		model.addAttribute("patente", new PatenteModel());
		return "user/formularioRegistroPatente";
	}
	
	@PostMapping("/guardarPatente")
	public String registrarPatente(@ModelAttribute PatenteModel patente) {
		patenteService.addPatente(patente);
		return "redirect:/user/index.html";
	}
}
