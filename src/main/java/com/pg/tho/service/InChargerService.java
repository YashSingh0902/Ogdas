package com.pg.tho.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.tho.entities.InCharge;
import com.pg.tho.repositories.IInChargerRepository;

public class InChargerService implements IInChargerService{

	@Autowired
	IInChargerRepository inChargerRepository;
	
	
		
	}


