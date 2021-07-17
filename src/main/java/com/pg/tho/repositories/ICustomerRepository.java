package com.pg.tho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pg.tho.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c inner join OrderDetails o on c.customerId=o.customer.customerId "
			+ "inner join Section s on o.section.sectionId=s.sectionId where "
			+ "s.sectionName=:sName")
	List<Customer> findBySectionName(@Param("sName") String sectionName);


	Customer findCustomerByEmail(String username);




	
}