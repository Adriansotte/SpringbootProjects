package com.examenValdivieso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenValdivieso.dao.JsonDao;
import com.examenValdivieso.model.JsonModel;

@Service
public class JsonServiceImpl implements JsonService{

	@Autowired
	private JsonDao jsonDao;
	
	@Override
	public JsonModel getJson() {
		return jsonDao.getJsonModel();
	}

}
