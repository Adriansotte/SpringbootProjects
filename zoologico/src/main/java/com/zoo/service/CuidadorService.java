package com.zoo.service;

import java.util.List;

import com.zoo.models.CuidadorModel;

public interface CuidadorService {

	List<CuidadorModel> listarCuidadores();
	void addCuidador(CuidadorModel cuidador);
	void deleteCuidador(CuidadorModel cuidador);
	CuidadorModel getCuidadorById(long id);
}
