package com.respuesta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WindgetModel {

	private String debug;

	private WindowModel window;

	private ImageModel image;

	private TextModel text;
}
