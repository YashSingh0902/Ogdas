package com.pg.tho.service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.tho.entities.Bill;
import com.pg.tho.entities.Customer;
import com.pg.tho.entities.FoodCart;
import com.pg.tho.entities.Item;
import com.pg.tho.entities.OrderDetails;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.repositories.IBillRepository;
import com.pg.tho.repositories.IOrderRepository;

@Service
@Transactional
public class BillService implements IBillService {
	
	@Autowired
    IBillRepository repository;
	
	@Autowired
	IOrderRepository repository2;
	
	Logger logger=LoggerFactory.getLogger(BillService.class);
	
	Customer customer;
	FoodCart foodCart;
	Item item;
	
	@Override
	public Bill addBill(OrderDetails order) {
	    
		logger.info("ADD Bill -> /./././");
		Bill bill=new Bill();
		List<Item> list=order.getList();	
		int total_item=list.size();
		int sum=0;
		for(int i=0;i<total_item;i++)
		{
			Item item=list.get(i);
			sum+=item.getCost();
		}
		
		bill.setBillDate(order.getOrderDate());
		bill.setOrder(order);
		bill.setTotalItem(total_item);
		bill.setTotalCost(sum);
		
		repository.save(bill);
		//repository.saveAndFlush(bill);
		return bill;
	}

	@Override
	public String removeBill(Bill bill) {
		
		logger.info("REMOVE Bill -> /./././");
		repository.delete(bill);
		String message="Bill removed Succesfully => DONE";
		return message;
	}

	@Override
	public Bill viewBillById(int id) {
		
		logger.info("VIEW Bill -> /./././");
		Bill bill=repository.findById(id).orElse(null);
		System.out.println(bill);
		return bill;
		
	}

	@Override
	public List<Bill> viewBillsBetweenDates(LocalDate startDate, LocalDate endDate) {
        
		logger.info("VIEW Bill (DATE) -> /./././");
		LocalDateTime startDateTime = startDate.atTime(0,0, 0);
        LocalDateTime endDateTime = endDate.atTime(23,59,59);
        List<Bill> bill=repository.findByBillDates(startDateTime, endDateTime);
        System.out.println(bill);
        return bill;
    }

	@Override
	public List<Bill> viewBillsByCustomerId(int custId) {
		
		logger.info("VIEW Bill (CUSTOMER) -> /./././");
		List<Bill> bill=repository.findByCustId(custId);
		System.out.println(bill);
		return bill;
	}

	@Override
	public String calculateTotalCost(Bill bill) {
		
		logger.info("TOTAL COST -> /./././");
		Double totalCost=bill.getTotalCost();
		StringBuffer sb=new StringBuffer("Total Cost for the specified bill is :");
		sb.append(totalCost);
		String message=sb.toString();
		return message;
	}
	
}
