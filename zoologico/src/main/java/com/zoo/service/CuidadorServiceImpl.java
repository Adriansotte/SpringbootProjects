package com.zoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.models.CuidadorModel;
import com.zoo.repository.CuidadorRepository;

@Service
public class CuidadorServiceImpl implements CuidadorService{

	@Autowired
	private CuidadorRepository cuidadorRepository;
	
	@Override
	public List<CuidadorModel> listarCuidadores() {
		return cuidadorRepository.findAll();
	}

	@Override
	public void addCuidador(CuidadorModel cuidador) {
		cuidadorRepository.save(cuidador);	
	}

	@Override
	public void deleteCuidador(CuidadorModel cuidador) {
		cuidadorRepository.delete(cuidador);	
	}

	@Override
	public CuidadorModel getCuidadorById(long id) {
		CuidadorModel cuidador = cuidadorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid producto Id:" + id));
		return cuidador;
	}
}
