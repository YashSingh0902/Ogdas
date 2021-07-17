package com.pg.tho.service;

import java.util.List;

import javax.validation.Valid;

import com.pg.tho.entities.Customer;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.removeFailedException;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	public List<Customer> viewAllCustomer(String sectionname);
	public String removeCustomerById(int id)  throws removeFailedException;
	public Customer CustomerLogin(String username, String password);
	public String CustomerLogout();
	public Customer viewCustomerById(int id)throws IdNotFoundException;
	public Customer updateCustomer(@Valid Customer customer);
	//public void updateCustomerName(Integer customerId, String customerName) throws Exception;
	//public void updateCustomerMail(Integer customerId, String email) throws Exception;
	
	 
}
