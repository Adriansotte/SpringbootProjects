package com.examenValdivieso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trabajo {

	private int id;
	
	private String desc;
	
	private String[] category;
	
	private double salary;
	
}
