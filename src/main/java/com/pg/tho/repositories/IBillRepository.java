package com.pg.tho.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pg.tho.entities.Bill;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {
	
    
	@Query("select b from Bill b "
     		+ "JOIN OrderDetails o "
     		+ "ON b.order.orderId=o.orderId "
     		+ "WHERE o.customer.customerId =:custId")	
    List<Bill> findByCustId(@Param("custId") int custId);
	
    
	@Query("select b  from Bill b where b.billDate BETWEEN ?1 AND ?2")
    public List<Bill> findByBillDates(LocalDate startDateTime, LocalDate endDateTime);

	@Query("select b  from Bill b where b.billDate BETWEEN ?1 AND ?2")
	public List<Bill> findByBillDates(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
