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

import com.pg.tho.entities.Customer;
import com.pg.tho.service.ICustomerService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/customerLogin")
public class CustomerLoginController {
	
	@Autowired
	ICustomerService customerService;
	
	Logger logger=LoggerFactory.getLogger(CustomerLoginController.class);
	
	@GetMapping("/customerLogin/{username}/{password}")
	public ResponseEntity<Customer> CustomerLogin(@PathVariable String username,@PathVariable String password) throws NameNotFoundException 
	{
		logger.info("CUSTOMER LOGIN  ->  ./././.");
		Customer login=customerService.CustomerLogin(username,password);
		if(login==null)
		{
			throw new NameNotFoundException("Username and/or Password is Incorrect");
		}
		return new ResponseEntity<Customer>(login, HttpStatus.OK);
	}
	
	@GetMapping("/customerLogout")
	public ResponseEntity<String> candidateLogout() 
	{
		logger.info("CUSTOMER LOGOUT  -> ./././.");
		String logout=customerService.CustomerLogout();
		return new ResponseEntity<String>(logout, HttpStatus.OK);
	}
}

