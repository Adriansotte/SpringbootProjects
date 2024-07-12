package com.examenValdivieso.model;

import java.util.ArrayList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonModel {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int identifier;
	
	private String fullName;
		
	private ArrayList<Trabajo> jobs;
	
	private String address;
	
	private int cp;
	
	private boolean disable;
}
