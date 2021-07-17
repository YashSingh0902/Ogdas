package com.pg.tho.controller;

import java.util.List;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.Customer;
import com.pg.tho.entities.OrderDetails;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.ICartService;
import com.pg.tho.service.IOrderService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	ICartService cartService;
	
	Logger logger=LoggerFactory.getLogger(OrderController.class);

	@PostMapping("/addOrder/{cartId}")
	public ResponseEntity<OrderDetails> addOrder(@PathVariable("cartId") int cartId)
	{
		logger.info("ADD Order -> /././././");
		OrderDetails addOrder =orderService.addOrder(cartId);
	   	return new ResponseEntity<OrderDetails>(addOrder,HttpStatus.OK);
	}
	
	@PutMapping("/updateOrder")
	public ResponseEntity<OrderDetails> updateOrder(@Valid @RequestBody OrderDetails order) throws IdNotFoundException
	{
		logger.info("UPDATE Order -> /././././");
		OrderDetails order1=orderService.viewOrderById(order.getOrderId());
		if(order1==null)
		{
			throw new IdNotFoundException("Updation failed - Inavlid order");
		}
		else
		{
			OrderDetails updateOrder =orderService.updateOrder(order);
			return new ResponseEntity<OrderDetails>(updateOrder,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/removeOrderByOrderId/{orderId}")
	public  ResponseEntity<String> removeOrder(@PathVariable("orderId") int oid) throws removeFailedException 
	{
		   logger.info("REMOVE Order -> /././././");
		   OrderDetails removeOrder=orderService.viewOrderById(oid);
	       if(removeOrder==null)
		   {
	    	   throw new removeFailedException("Deletion failed to perform - Invalid Order");
	       }
	       else
	       {
	    	   String message=orderService.removeOrderById(removeOrder);
	    	   return new ResponseEntity<String>(message, HttpStatus.OK);
	       }
	}
	
	@GetMapping("/viewOrderByOrderId/{orderId}")
	public ResponseEntity<OrderDetails> viewOrder(@PathVariable("orderId") int oid) throws IdNotFoundException 
	{
		logger.info("VIEW Order -> /././././");
		OrderDetails viewByOrderId = orderService.viewOrderById(oid);
		if(viewByOrderId==null)
		{
			throw new IdNotFoundException("Order not found - Place a new order");
		}
		else
		{
			return new ResponseEntity<OrderDetails>(viewByOrderId, HttpStatus.OK);
		}
	
	}
	
	@GetMapping("/viewAllOrdersBySection/{sectionId}")
	public ResponseEntity<List<OrderDetails>> viewAllOrdersBySection(@PathVariable("sectionId") int sectId) /*throws IdNotFoundException */
	{
		logger.info("VIEW Order (SECTION)  -> /././././");
		List<OrderDetails> viewBySectId= orderService.viewAllOrdersBySection(sectId);
		/*if(order2.isEmpty())
		{
			throw new IdNotFoundException("Section is Invalid - Retry");
		}
		else
		{*/
			return new ResponseEntity<List<OrderDetails>>(viewBySectId, HttpStatus.OK);

		//}
	}
	
	@GetMapping("/viewAllOrdersByCustomer/{customerId}")
	public ResponseEntity<List<OrderDetails>> viewAllOrdersByCustomer(@PathVariable("customerId") int id) throws IdNotFoundException 
	{
		logger.info("VIEW Order (CUSTOMER)  -> /././././");
		List<OrderDetails> viewByCustomerId = orderService.viewAllOrdersByCustomer(id);
		if(viewByCustomerId.isEmpty())
		{
			throw new IdNotFoundException("Invalid CustomerId - Try again with diffrerent Id");
		}
		else
		{
			return new ResponseEntity<List<OrderDetails>>(viewByCustomerId, HttpStatus.OK);
		}
	}
	
	
}
