package com.zoo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuidador")
public class CuidadorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCuidador;

	private String nombreCompleto;

	private String Dni;

	private int edad;

	// OJO QUE CON LOS BOOLEANS LOS GETTERS CAMBIAN A ISACTIVO O IS GENERO
	private boolean genero;

	// OJO QUE CON LOS BOOLEANS LOS GETTERS CAMBIAN A ISACTIVO O IS GENERO
	private boolean activo;
}