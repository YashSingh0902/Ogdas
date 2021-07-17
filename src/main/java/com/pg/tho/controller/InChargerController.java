package com.pg.tho.controller;


import javax.naming.NameNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.Section;
import com.pg.tho.service.ISectionService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/section")
public class InChargerController {
		
		@Autowired
		ISectionService service;
		
		Logger logger=LoggerFactory.getLogger(InChargerController.class);
		
		@GetMapping("/sectionLogin/{username}/{password}")
		public ResponseEntity<Section> InChargerLogin(@PathVariable String username,@PathVariable String password) throws NameNotFoundException 
		{
			logger.info("ADMIN LOGIN  ->>>> /*/*/*");
			Section login=service.InChargeLogin(username,password);
			if(login==null)
			{
				throw new NameNotFoundException("Username and/or is Incorrect");
			}
			return new ResponseEntity<Section>(login, HttpStatus.OK);
		}
		
		@GetMapping("/SectionLogout")
		public ResponseEntity<String> InChargerLogout() 
		{
			logger.info("ADMIN LOGOUT ->>>> /*/*/*");
			String logout=service.InChargeLogout();
			return new ResponseEntity<String>(logout, HttpStatus.OK);
		}
}