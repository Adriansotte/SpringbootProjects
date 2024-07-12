package com.zoo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnimal;

	private String nombre;

	private String pais;

	private int altura;

	private int peso;

	private String comidaFav;

	@ManyToOne
	@JoinColumn(name = "idCuidador")
	private CuidadorModel cuidador;
}