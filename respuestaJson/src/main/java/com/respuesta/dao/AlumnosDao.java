package com.respuesta.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.respuesta.model.AlumnoModel;
import com.respuesta.model.ImageModel;
import com.respuesta.model.ProfesorModel;
import com.respuesta.model.TextModel;
import com.respuesta.model.WindgetModel;
import com.respuesta.model.WindowModel;

@Component
public class AlumnosDao {

	private AlumnoModel alumno;
	
	public AlumnoModel getAlumno(){
		WindowModel window = new WindowModel("Sample Komfabulator Widget", "main_window", 500, 500);
		ImageModel image = new ImageModel("Images/Sun.png", "sun1", 250, 250, "center");
		TextModel text = new TextModel("Click here", 36, "bold", "text1", 250, 100, "center", "sun1.opacity = (sun1.opacity / 100) * 90");
		WindgetModel widget = new WindgetModel("on", window, image, text);
		AlumnoModel nuevo = new AlumnoModel(widget);
		return nuevo;
	}
}
