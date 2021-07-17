package com.pg.tho.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.Bill;
import com.pg.tho.entities.OrderDetails;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.IBillService;
import com.pg.tho.service.IOrderService;

@CrossOrigin(origins="http://localhost:3000")     //indicate allow access the resources from another domain
@RestController                                  //creating restful controllers
@RequestMapping(value = "/bill")                    //use to map web request
public class BillController {
	
	@Autowired                                       //used for dependency injection
	IBillService billService;
	
	@Autowired
	IOrderService orderService;
	
	Logger logger=LoggerFactory.getLogger(BillController.class);
	
	@PostMapping("/addBill/{OrderId}")                            //create a new resource
	public ResponseEntity<Bill> addBill(@PathVariable("OrderId") int id) throws IdNotFoundException    //indicates that a method parameter 
	                                                                                                   // should be bound to uri
	{ 
		logger.info("ADD Bill -> ././././");
		OrderDetails orders=orderService.viewOrderById(id);
		Bill bill2=billService.addBill(orders);
		if(bill2==null)
		{
			throw new IdNotFoundException("Cannot add order");
		}
		else
		return new ResponseEntity<Bill>(bill2,HttpStatus.OK);
		
	}
		

	
	
	@GetMapping("/viewBillByBillId/{BillId}")        //get request onto specific methods, used to pass data to rest controller
	public ResponseEntity<Bill> viewBillById(@PathVariable("BillId") int id) throws IdNotFoundException
	{ 
		logger.info("VIEW Bill (BILL_ID)  -> ././././");
		Bill bill2=billService.viewBillById(id);
		if(bill2==null)
		{
			throw new IdNotFoundException("Invalid Bill Id - Enter valid Id ");
		}
		else
		{
			return new ResponseEntity<Bill>(bill2,HttpStatus.OK);     //represents an http response
		}
	}
	
	
	@GetMapping("/viewBillByCustomerId/{CustomerId}")
	public ResponseEntity<List<Bill>>  viewBillsByCustomerId(@PathVariable("CustomerId") int id) throws IdNotFoundException{
		
		logger.info("VIEW Bill (CUSTOMER_ID) -> ././././");
		List<Bill> billList = billService.viewBillsByCustomerId(id);
		if(billList==null)
		{
			throw new IdNotFoundException("Invalid Customer Id - Enter valid Id ");
		}
		else
		{
			return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);                                  
		}
	}
	
	
//	@GetMapping("/viewBillByOrderDate/{StartDate}/{EndDate}")
//	public ResponseEntity<List<Bill>> viewBillsBetweenDates(@PathVariable String Start_date, @PathVariable String End_date){
//		
//		logger.info("VIEW Bill date  -> ././././");
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate startDate = LocalDate.parse(Start_date, dateTimeFormatter);
//		LocalDate endDate = LocalDate.parse(End_date, dateTimeFormatter);
//		List<Bill> billList = billService.viewBillsBetweenDates(startDate,endDate);
//	
//		return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);
//	}
//	
	
	@GetMapping("/calculateTotalCost/{BillId}")
	public ResponseEntity<String> calculateTotalCost(@PathVariable("BillId") int id) throws IdNotFoundException{
		
		logger.info("Calculate TOTAL COST -> ././././");
		Bill bill=billService.viewBillById(id);
		if(bill==null)
		{
			throw new IdNotFoundException("Invalid BillId (Unable to calculate Total_Cost)");
		}
		else
		{
			String billList = billService.calculateTotalCost(bill);
		    return new ResponseEntity<String>(billList, HttpStatus.OK);
		}
		    
	}
}

