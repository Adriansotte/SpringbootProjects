package com.respuesta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextModel {

	private String data;
	
	private int size;
	
	private String style;
	
	private String name;
	
	private int hOffset;
	
	private int vOffset;
	
	private String alignment;
	
	private String onMouseUp;
}
