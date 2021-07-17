package com.pg.tho.test;
//package com.pg.tho.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.pg.tho.entities.Address;
//import com.pg.tho.entities.Bill;
//import com.pg.tho.entities.Customer;
//import com.pg.tho.entities.OrderDetails;
//import com.pg.tho.entities.Section;
//import com.pg.tho.repositories.IBillRepository;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//class BillServiceTest {
//
//	@Autowired
//	IBillRepository repository;
//	
////
////	@Test
////	void testRemoveBill() {
////		repository.deleteById(20);
////	}
////
////	@Test
////	void testViewBillById() {
////		Bill bill=repository.findById(20).orElse(null);
////		assertNotNull(bill);
////	}
//
//
//
////	@Test
////	void testViewBillsByCustomerId() {
////		List<Bill> list=repository.findByCustId(16);
////		int cnt=list.size();
////		boolean res=false;
////		if(cnt>0)
////		{
////			res=true;
////		}
////		assertTrue(res);
////
////	}
//
////	@Test
////	void testCalculateTotalCost() {
////		Bill bill=new Bill();
////		Double totalCost=bill.getTotalCost();
////		assertNotNull(totalCost);
////	}
//	
//	public Customer getCustomer()
//	{
//		Customer cust=new Customer();
//		cust.setAge(22);
//		cust.setCustomerId(16);
//		cust.setEmail("g@gmail.com");
//		cust.setFullName("Guddu");
//		cust.setGender("Female");
//		cust.setMobileNumber("9348025426");
//		return cust;
//	}
//	public Address getAddress()
//	{
//		Address add=new Address();
//		add.setAddressId(2);
//		add.setArea("Hadapsar");
//		add.setBuildingName("Pebble");
//		add.setCity("Pune");
//		add.setCountry("India");
//		add.setPincode("41160");
//		add.setState("Maharashtra");
//		add.setStreetNo("93");
//		return add;
//	}
//	public Section getSection()
//	{
//		Section sec=new Section();
//	    sec.setInChargeName("ravi");
//	    sec.setSectionId(3);
//	    sec.setSectionName("Liquor");
//	    return sec;
//	}
//	public OrderDetails getOrder()
//	{
//		OrderDetails ord=new OrderDetails();
//		ord.setCustomer(getCustomer());
//		ord.setList(null);
//		ord.setOrderDate(LocalDate.now());
//		ord.setOrderId(11);
//		ord.setOrderStatus("delivered");
//		ord.setSection(getSection());
//		return ord;
//	}
//	 public Bill getBill()
//	 {
//		 Bill bill=new Bill();
//		 bill.setBillDate(LocalDate.now());
//		 bill.setBillId(20);
//		 bill.setOrder(getOrder());
//		 bill.setTotalCost(90);
//		 bill.setTotalItem(1);
//		 return bill;
//	 }
//
//
//}
