package com.examenValdivieso.service;
import java.util.List;

import com.examenValdivieso.model.InventoModel;

public interface InventoService {

	List<InventoModel> listarInventos();
	void addInveto(InventoModel invento);
	void deleteInvento(InventoModel invento);
	InventoModel getInventoById(long id);
	
	
	
}
