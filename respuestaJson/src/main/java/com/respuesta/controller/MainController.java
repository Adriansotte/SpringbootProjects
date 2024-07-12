package com.respuesta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.respuesta.model.AlumnoModel;
import com.respuesta.services.AlumnosService;
import org.springframework.http.HttpStatus;

@RestController
public class MainController {

	@Autowired
	private AlumnosService alumnoService;

	// Response entity

	@GetMapping("/")
	public ResponseEntity<AlumnoModel> getAlumno() {
		AlumnoModel alumno = alumnoService.getAlumno();
		return new ResponseEntity<>(alumno, HttpStatus.OK);
	}
}
