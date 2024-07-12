package com.zoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.models.AnimalModel;
import com.zoo.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	public List<AnimalModel> listarAnimales() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animalRepository.findAll();
	}

	@Override
	public void addAnimal(AnimalModel animal) {
		animalRepository.save(animal);
	}

	@Override
	public void deleteAnimal(AnimalModel animal) {
		animalRepository.delete(animal);	
	}

	@Override
	public AnimalModel getAnimalById(long id) {
	    //Java.util
		Optional<AnimalModel> optionalAnimal = animalRepository.findById(id);
	    if (optionalAnimal.isPresent()) {
	        return optionalAnimal.get();
	    } else {
	        return null; // Devuelve null si no se encuentra ningún animal con el ID proporcionado
	    }
	}


}
