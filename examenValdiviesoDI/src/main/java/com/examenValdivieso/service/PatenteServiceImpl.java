package com.examenValdivieso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenValdivieso.model.PatenteModel;
import com.examenValdivieso.repository.PatenteRepository;

@Service
public class PatenteServiceImpl implements PatenteService {

	@Autowired
	private PatenteRepository patenteReposiroty;

	@Override
	public List<PatenteModel> listarPatentes() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return patenteReposiroty.findAll();
	}

	@Override
	public void addPatente(PatenteModel patente) {
		patenteReposiroty.save(patente);
		
	}

	@Override
	public void deletePatente(PatenteModel patente) {
		patenteReposiroty.delete(patente);
		
	}

	@Override
	public PatenteModel getPatenteById(long id) {
		Optional<PatenteModel> optionalPatente = patenteReposiroty.findById(id);
		if(optionalPatente.isPresent()) {
			return optionalPatente.get();
		} else {
			return null;
		}
	}
	
}
