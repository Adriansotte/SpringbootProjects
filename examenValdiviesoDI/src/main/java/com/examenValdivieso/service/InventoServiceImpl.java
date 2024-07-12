package com.examenValdivieso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenValdivieso.model.InventoModel;
import com.examenValdivieso.repository.InventoRepository;

@Service
public class InventoServiceImpl implements InventoService{

	@Autowired
	private InventoRepository inventoRepository;
	
	@Override
	public List<InventoModel> listarInventos() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventoRepository.findAll();
	}

	@Override
	public void addInveto(InventoModel invento) {
		inventoRepository.save(invento);
		
	}

	@Override
	public void deleteInvento(InventoModel invento) {
		inventoRepository.delete(invento);
		
	}

	@Override
	public InventoModel getInventoById(long id) {
		Optional<InventoModel> optionalInvento = inventoRepository.findById(id);
		if(optionalInvento.isPresent()) {
			return optionalInvento.get();
		} else {
			return null;
		}
	
	}

}
