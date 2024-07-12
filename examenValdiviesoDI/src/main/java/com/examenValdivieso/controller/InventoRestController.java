package com.examenValdivieso.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examenValdivieso.model.InventoModel;
import com.examenValdivieso.model.JsonModel;
import com.examenValdivieso.model.PatenteModel;
import com.examenValdivieso.service.InventoService;
import com.examenValdivieso.service.JsonService;
import com.examenValdivieso.service.PatenteService;


@RestController
public class InventoRestController {

	@Autowired
	private InventoService inventoService;
	
	@Autowired
	private PatenteService patenteService;
	
	@Autowired
	private JsonService jsonService;
	
	@GetMapping("/todosInventos")
	public ResponseEntity<List<InventoModel>> mostrarInventos() {
		List<InventoModel> lista = inventoService.listarInventos();
		return new ResponseEntity<>(lista, HttpStatus.OK);	
	}
	
	@PostMapping("/crearInvento")
	public ResponseEntity<InventoModel> crearInvento (@RequestBody InventoModel invento){
		inventoService.addInveto(invento);
		return new ResponseEntity<>(invento, HttpStatus.OK);
	}
	
	@GetMapping("/borrarInvento/{id}")
	public ResponseEntity<?> borrarInvento(@PathVariable("id") Long id) {
		InventoModel invento = inventoService.getInventoById(id);
		if (invento == null) {
			return ResponseEntity.notFound().build();
		} else {
			List<PatenteModel> lista = patenteService.listarPatentes();
			actualizarPatentes(patenteService.listarPatentes(), invento);		
			inventoService.deleteInvento(invento);
			return ResponseEntity.ok(invento);
		}		
	}
	
//	private void actualizarPatentes(List<PatenteModel> patentes, InventoModel invento) {
//		for (int i = 0; i < patentes.size(); i++) {
//			if (patentes.get(i).getInvento() == invento) {
//				patenteService.deletePatente(patentes.get(i));
//				
//				patentes.get(i).setInvento(null);
//				patenteService.addPatente(patentes.get(i));
//			}
//		}
//	}
	
//	De esta manera lo borraria pero no me ha dado tiempo a probarlo, da un error en la pagina pero si que funciona si se revisa la pagina web
	private void actualizarPatentes(List<PatenteModel> patentes, InventoModel invento) {
		for (int i = 0; i < patentes.size(); i++) {
			if (patentes.get(i).getInvento() == invento) {
				patenteService.deletePatente(patentes.get(i));
			}
		}
	}
	
	//Aqui se hace la llamada a nuestro objeto json
	@GetMapping("/respuestaJson")
	public ResponseEntity<JsonModel> devolverJson() {
		JsonModel json = jsonService.getJson();
		return new  ResponseEntity<>(json, HttpStatus.OK);
	}
	
	
	
}
