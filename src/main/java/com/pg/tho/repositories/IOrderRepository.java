package com.pg.tho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pg.tho.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

	
	@Query("select o from OrderDetails o where o.customer.customerId=:id")
	public List<OrderDetails> findAllOrdersByCustomer(@Param("id") int id);

    @Query("select o from OrderDetails o where o.section.sectionId=(select s.sectionId from Section s where s.sectionName=:name)")
	public List<OrderDetails> findAllBySection(@Param("name") String secName);
	
	@Query("select o from OrderDetails o where o.section.sectionId=(select s.sectionId from Section s where s.sectionId=:sectId)")
	public List<OrderDetails> findAllBySection(@Param("sectId") int sectId);

	
}
