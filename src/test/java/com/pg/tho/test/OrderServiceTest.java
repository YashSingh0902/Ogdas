package com.pg.tho.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pg.tho.entities.Address;
import com.pg.tho.entities.Customer;
import com.pg.tho.entities.OrderDetails;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.IOrderRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class OrderServiceTest {

	@Autowired
	IOrderRepository repository;
	


//	@Test
//	void testRemoveOrderById() {
//		repository.deleteById(19);
//	}

//	@Test
//	void testViewOrderById() {
//		OrderDetails order=repository.findById(19).orElse(null);
//		assertNotNull(order);
//	}

//	@Test
//	void testViewAllOrdersByCustomer() {
//		List<OrderDetails> list=repository.findAllOrdersByCustomer(16);
//		int cnt=list.size();
//		boolean res=false;
//		if(cnt>0)
//		{
//			res=true;
//		}
//		assertTrue(res);
//		}

	@Test
	void testViewAllOrdersBySection() {
		List<OrderDetails> list=repository.findAllBySection("Pulses");
		assertNotNull(list);

	}

	public Customer getCustomer()
	{
		Customer cust=new Customer();
		cust.setAge(22);
		cust.setCustomerId(1);
		cust.setEmail("Amit@gmail.com");
		cust.setFullName("Amit");
		cust.setGender("M");
		cust.setMobileNumber("987456242");
		return cust;
	}
	public Address getAddress()
	{
		Address add=new Address();
		add.setAddressId(2);
		add.setArea("Hadapsar");
		add.setBuildingName("Pebble");
		add.setCity("Pune");
		add.setCountry("India");
		add.setPincode("41160");
		add.setState("Maharashtra");
		add.setStreetNo("93");
		return add;
	}
	public Section getSection()
	{
		Section sec=new Section();
	    sec.setInChargeName("ravi");
	    sec.setSectionId(4);
	    sec.setSectionName("Liqour");
	    return sec;
	}
	public OrderDetails getOrder()
	{
		OrderDetails ord=new OrderDetails();
		ord.setCustomer(getCustomer());
		ord.setList(null);
		ord.setOrderDate(LocalDate.now());
		ord.setOrderId(11);
		ord.setOrderStatus("delivered");
		ord.setSection(getSection());
		return ord;
	}
}
