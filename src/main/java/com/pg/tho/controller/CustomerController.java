package com.pg.tho.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.Customer;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.invalidNameException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.ICustomerService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	private Environment environment;
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/addCustomer") 
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) 
	{
		logger.error("ADD Customer -> ././././");
	    Customer addCustomer=customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(addCustomer,HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws IdNotFoundException
	{
		logger.info("UPDATE Customer -> ././././");
		Customer customer1=customerService.viewCustomerById(customer.getCustomerId());
		if(customer1==null)
		{
			throw new IdNotFoundException("Invalid Customer Id - Retry");
		}
		else
		{
			Customer updateCustomer=customerService.updateCustomer(customer);
			return new ResponseEntity<Customer>(updateCustomer,HttpStatus.OK);
		}
	}
	
	/*@PutMapping(value = "updateCustomer/{customerId}")
	// @ApiOperation("Updates the customer name by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateCustomerName(@PathVariable Integer customerId, @RequestBody Customer name) throws Exception {
		customerService.updateCustomerName(customerId, name.getFullName());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	

	@PutMapping(value = "updateCustomerMail/{customerId}")
	// @ApiOperation("Updates the customer mail by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateCustomerMail(@PathVariable Integer customerId, @RequestBody Customer mail) throws Exception {
		customerService.updateCustomerMail(customerId, mail.getEmail());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}*/
	
	@DeleteMapping("/removeCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int id) throws removeFailedException, IdNotFoundException
	{
		logger.info("REMOVE Customer -> ././././");
		Customer customer=customerService.viewCustomerById(id);
		if(customer==null)
		{
			throw new removeFailedException("Invalid Customer Id - Unable to perform deletion");
		}
		else
		{
			String removecustomer=customerService.removeCustomerById(id);
			return new ResponseEntity<String>(removecustomer,HttpStatus.OK);
		}
			
	}
	
	@GetMapping("/viewCustomer/{customerId}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("customerId") int id) throws IdNotFoundException
	{
		logger.info("VIEW Customer -> ././././");
		Customer viewCustomer=customerService.viewCustomerById(id);
	    if(viewCustomer==null)
	    {
	    	throw new IdNotFoundException("CustomerId doesn't exist - Retry with different Id");
	    }
		return new ResponseEntity<Customer>(viewCustomer,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllCustomerBySection/{sectionName}")
	public ResponseEntity<List<Customer>> viewAllCustomer(@PathVariable("sectionName") String name) throws invalidNameException
	{
		logger.info("VIEW Customer (SECTION)  -. ././././");
	    List<Customer> customer=customerService.viewAllCustomer(name);
	    if(customer.isEmpty())
	    {
	    	throw new invalidNameException("Invalid Section - Try again");
	    }
		return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
}
