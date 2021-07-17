package com.pg.tho.service;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.tho.controller.OrderController;
import com.pg.tho.entities.FoodCart;
import com.pg.tho.entities.Item;
import com.pg.tho.entities.OrderDetails;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.ICartRepository;
import com.pg.tho.repositories.IItemRepository;
import com.pg.tho.repositories.IOrderRepository;

@Service
@Transactional
public class OrderService implements IOrderService{
	
	@Autowired
	IOrderRepository repository;
	
	@Autowired
	ICartRepository repo2;
	
	@Autowired
	IItemRepository repo3;	
	
	@Autowired
	CartService service;

	Logger logger=LoggerFactory.getLogger(OrderService.class);
	

	@Override
	public OrderDetails addOrder(int cartId) {
		
		logger.info("ADD Order -> /./././");
		OrderDetails order=new OrderDetails();
		FoodCart cart=repo2.findById(cartId).orElse(null);
		List<Item> orderList=new ArrayList<Item>();
		
		List<Item> item1=cart.getItemList();
		int list_size=item1.size();
		for(int i=0;i<list_size;i++)
		{
			Item item=item1.get(i);
			orderList.add(item);
		}
		Section sec=item1.get(0).getSection();
		order.setCustomer(cart.getCustomer());
		order.setSection(sec);
		order.setList(orderList);
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("pending");
		repository.save(order);
		service.clearCart(cartId);		
		return order;
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		
		logger.info("UPDATE Order -> /./././");
		OrderDetails order1=repository.save(order);
		return order1;
	}

	@Override
	public String removeOrderById(OrderDetails order) {
	
        logger.info("REMOVE Bill -> /./././");
		repository.delete(order);
		return "Order is removed => DONE";
	}

	@Override
	public OrderDetails viewOrderById(int id) {
		
		logger.info("VIEW Order -> /./././");
		OrderDetails order=repository.findById(id).orElse(null);
		System.out.println("The Orders are listed :" + order.getList());
		order.setList(order.getList());
		return order;
	}
	
	@Override
	public List<OrderDetails> viewAllOrdersByCustomer(int id) {
	
		logger.info("VIEW Order (CUSTOMER)  -> /./././");
		List<OrderDetails> list = repository.findAllOrdersByCustomer(id);
		System.out.println(list);
		return list;
	}

	/*@Override
	public List<OrderDetails> viewAllOrders(int id) {
	
		List<OrderDetails> list = repository.findAll(id);
		
		return list;
	}*/

	@Override
	public List<OrderDetails> viewAllOrdersBySection(int sectId) {
		
		logger.info("VIEW Order (SECTION)   -> /./././");
		List<OrderDetails> list = repository.findAllBySection(sectId);
		System.out.println(list);
		return list;
	}
	
}

	
	
