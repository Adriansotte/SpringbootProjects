package com.respuesta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respuesta.dao.AlumnosDao;
import com.respuesta.model.AlumnoModel;

@Service
public class AlumnosServiceImpl implements AlumnosService{

	@Autowired
	AlumnosDao alumnosDao;

	@Override
	public AlumnoModel getAlumno() {
		return alumnosDao.getAlumno();
	}	
}
