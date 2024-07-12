package com.examenValdivieso.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.examenValdivieso.model.JsonModel;
import com.examenValdivieso.model.Trabajo;

@Component
public class JsonDao {

	
	public JsonModel getJsonModel() {
		ArrayList<Trabajo> jobs = new ArrayList<>();
		Trabajo uno = new Trabajo(1002, "DEVOPS", new String[] {"Dev", "OPS"}, 1200.50);
		jobs.add(uno);
		JsonModel aEnviar = new JsonModel(1, "luis perez", jobs, "c.juan Bosco, 23", 50009, true);		
		return aEnviar;
	}
}
