package com.zoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoo.models.AnimalModel;

public interface AnimalRepository extends JpaRepository<AnimalModel, Long>{

}
