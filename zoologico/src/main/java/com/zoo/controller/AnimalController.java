package com.zoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.models.AnimalModel;
import com.zoo.service.AnimalService;

@RestController
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	@GetMapping("/todosAnimales")
	@Cacheable(value="zoo")
	public ResponseEntity<List<AnimalModel>> mostrarAnimales() {
		List<AnimalModel> lista = animalService.listarAnimales();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/crearAnimal")
	@CacheEvict(value="zoo", allEntries = true)
	public ResponseEntity<AnimalModel> crearAnimal(@RequestBody AnimalModel animal) {
	    animalService.addAnimal(animal);
	    return new ResponseEntity<>(animal, HttpStatus.OK);
	}

	@GetMapping("/borrarAnimal/{id}")
	@CacheEvict(value="zoo", allEntries = true)
	public ResponseEntity<?> borrarAnimal(@PathVariable("id") Long id) {
	    AnimalModel animal = animalService.getAnimalById(id);
	    if (animal == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        animalService.deleteAnimal(animal);
	        return ResponseEntity.ok(animal);
	    }
	}
	
	@PostMapping("/actualizarAnimal/{id}")
	@CacheEvict(value="zoo", allEntries = true)
	public ResponseEntity<AnimalModel> actualizarAnimal(@PathVariable("id") Long id, @RequestBody AnimalModel animal) {
	    // Verificar si el animal existe antes de actualizar
	    AnimalModel animalExistente = animalService.getAnimalById(id);
	    if (animalExistente == null) {
	        return ResponseEntity.notFound().build(); // Devolver 404 si no se encuentra el animal
	    }
	    // Actualizar los campos del animal existente con los valores proporcionados
	    animalExistente.setNombre(animal.getNombre());
	    animalExistente.setAltura(animal.getAltura());
	    animalExistente.setComidaFav(animal.getComidaFav());
	    animalExistente.setCuidador(animal.getCuidador());
	    animalExistente.setPais(animal.getPais());
	    animalExistente.setPeso(animal.getPeso());

	    // Guardar el animal actualizado en la base de datos
	    animalService.addAnimal(animalExistente);

	    return ResponseEntity.ok(animalExistente); // Devolver 200 OK con el animal actualizado
	}

}
