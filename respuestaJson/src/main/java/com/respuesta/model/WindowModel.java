package com.respuesta.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WindowModel {

	private String title;
	
	private String name;
	
	private int width;
	
	private int height;
	
}
