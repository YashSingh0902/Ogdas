
package com.pg.tho.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.tho.entities.Category;
import com.pg.tho.entities.Customer;
import com.pg.tho.entities.FoodCart;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.repositories.ICartRepository;
import com.pg.tho.repositories.ICustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository repository;
	
	@Autowired
	ICartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	@Override
	public Customer addCustomer(Customer customer)  {
		
		logger.info("ADD Customer  -> /./././");
		repository.save(customer);
		FoodCart cart= new FoodCart();
		cart.setCustomer(customer);
		cartRepository.save(cart);
		return customer;
	}


	@Override
	public Customer updateCustomer(Customer customer) {
		
		logger.info("UPDATE Customer  -> /./././");
		repository.save(customer);
		return customer;
	}
	
	/*@Override
	public void updateCustomerName(Integer customerId, String customerName) throws Exception {
		// TODO Auto-generated method stub
		Optional<Customer> updateName = repository.findById(customerId);
		Customer name = updateName.orElseThrow(() -> new Exception("Service.CUSTOMER_NOT_FOUND"));
		name.setFullName(customerName);
		
	}
	
	@Override
	public void updateCustomerMail(Integer customerId, String email) throws Exception {
		// TODO Auto-generated method stub
		Optional<Customer> updateMail = repository.findById(customerId);
		Customer mail = updateMail.orElseThrow(() -> new Exception("Service.CUSTOMER_NOT_FOUND"));
		mail.setEmail(email);
		
	}*/



	@Override
	public String removeCustomerById(int id) {
	
		logger.info("REMOVE Customer  -> /./././");
		int cartId=cartRepository.findcartByCustomerId(id);
		cartService.clearCart(cartId);
		cartRepository.deleteById(cartId);
		repository.deleteById(id);
		String message="Removal of Customer => DONE";
		return message;
	}

	@Override
	public Customer viewCustomerById(int id) {
		
		logger.info("VIEW Customer  -> /./././");
		Customer customer=repository.findById(id).orElse(null);
		return customer;
	}

	@Override
	public List<Customer> viewAllCustomer(String sectionName) {
		
		logger.info("VIEW ALL Customer  -> /./././");
		List<Customer> list=repository.findBySectionName(sectionName);
		return list;
	}

	@Override
	public Customer CustomerLogin(String username, String password) {

		logger.info("CUSTOMER LOGIN => /*/*/*");
		Customer customer=repository.findCustomerByEmail(username);
		String usr=customer.getEmail();
		String pwd=customer.getPassword();
		if(customer!=null)
		{
			if(usr.equals(username) && pwd.equals(password))
			{
				return customer;
			}
		}
		return null;
	}

	@Override
	public String CustomerLogout() {
		return "You are LoggedOut sucessfully. Visit Again \n THANK YOU";
	}


	









}
