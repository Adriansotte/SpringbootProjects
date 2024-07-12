package com.zoo.service;

import java.util.List;

import com.zoo.models.AnimalModel;

public interface AnimalService {

	List<AnimalModel> listarAnimales();
	void addAnimal(AnimalModel animal);
	void deleteAnimal(AnimalModel animal);
	AnimalModel getAnimalById(long id);
}
