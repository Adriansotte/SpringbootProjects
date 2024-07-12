package com.respuesta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {

	private String src;
	
	private String name;
	
	private int hOffset;
	
	private int vOffset;
	
	private String alignment;
}
