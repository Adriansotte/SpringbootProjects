package com.examenValdivieso.service;
import java.util.List;

import com.examenValdivieso.model.PatenteModel;


public interface PatenteService {

	List<PatenteModel> listarPatentes();
	void addPatente(PatenteModel patente);
	void deletePatente(PatenteModel patente);
	PatenteModel getPatenteById(long id);
	
}
