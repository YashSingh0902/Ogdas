package com.pg.tho.test;
//package com.pg.tho.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.pg.tho.entities.Address;
//import com.pg.tho.entities.Customer;
//import com.pg.tho.repositories.ICustomerRepository;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//class CustomerServiceTest {
//
//	@Autowired
//	ICustomerRepository repository;
//	
//	@Test
//	void testAddCustomer() {
//		
//		Customer customer=repository.save(getCustomer());
//		assertNotNull(customer);
//	}
//
//	@Test
//	void testUpdateCustomer() {
//		
//		Customer customer=repository.save(getCustomer());
//		assertNotNull(customer);
//	}
//
////	@Test
////	void testViewCustomerById() {
////
////		Customer customer=repository.findById(1).orElse(null);
////		assertNotNull(customer);
////	}
////
////	@Test
////	void testViewAllCustomer() {
////		
////		List<Customer> list=repository.findAll();
////		int cnt=list.size();
////		boolean res=false;
////		if(cnt>0)
////		{
////			res=true;
////		}
////		assertTrue(res);
////	}
////	
////	
////	public Customer getCustomer()
////	{
////		Customer cust=new Customer();
////		cust.setAge(22);
////		cust.setCustomerId(16);
////		cust.setEmail("Amit@gmail.com");
////		cust.setFullName("Amit");
////		cust.setGender("M");
////		cust.setMobileNumber("987456242");
////		return cust;
////	}
////	
////	
////	public Address getAddress()
////	{
////		Address add=new Address();
////		add.setAddressId(2);
////		add.setArea("Hadapsar");
////		add.setBuildingName("Pebble");
////		add.setCity("Pune");
////		add.setCountry("India");
////		add.setPincode("41160");
////		add.setState("Maharashtra");
////		add.setStreetNo("93");
////		return add;
////	}
////
////}
