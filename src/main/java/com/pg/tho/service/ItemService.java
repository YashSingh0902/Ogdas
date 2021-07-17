package com.pg.tho.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.tho.entities.Customer;
import com.pg.tho.entities.Item;
import com.pg.tho.repositories.IItemRepository;

@Service
@Transactional
public class ItemService implements IItemService{

	@Autowired
	IItemRepository repository;
	
	Logger logger=LoggerFactory.getLogger(ItemService.class);
	
	@Override
	public Item addItem(Item item) {
		
		logger.info("ADD Item -> /./././");
		Item item1 = repository.save(item);	
		return item1;
	}

	@Override
	public Item updateItem(Item item) {
		
		logger.info("UPDATE Item -> /./././");
		Item item1=repository.save(item);
		return item1;
	}
	
	/*@Override
	public void updateItem(Integer itemId, int quantity) throws Exception {
		// TODO Auto-generated method stub
		Optional<Item> itemQuantity = repository.findById(itemId);
		Item itemquant = itemQuantity.orElseThrow(() -> new Exception("Service.ITEM_NOT_FOUND"));
		itemquant.setQuantity(quantity);
		
	}
	
	@Override
	public void updateCost(Integer itemId, double cost) throws Exception {
		// TODO Auto-generated method stub
		Optional<Item> itemCost = repository.findById(itemId);
		Item itemcost = itemCost.orElseThrow(() -> new Exception("Service.ITEM_NOT_FOUND"));
		itemcost.setCost(cost);
		
	}*/

	@Override
	public String removeItem(Item item) {
		
		logger.info("ADD Item -> /./././");
		repository.delete(item);
		return "Removal of Item => DONE";
	}



	@Override
	public List<Item> viewAllItemsByCategory(String name) {
		
		logger.info("VIEW Item (CATEGORY)  -> /./././");
		List<Item> list=repository.findItemsByCategory(name);
		return list;
		
	}

	@Override
	public List<Item> viewAllItemsByItemName(String name) {
		
		logger.info("VIEW Item (NAME)  -> /./././");
		List<Item> list=repository.findItemsByItemName(name);
		return list;
	}

	
	@Override
	public Item viewItemById(int id) {
	
		logger.info("VIEW Item (ID)  -> /./././");
		Item item=repository.findById(id).orElse(null);
		return item;
	}

	@Override
	public List<Item> findItemsBySections(String name) {
		logger.info("FIND Item (SECTION)  -> /./././");
		List<Item> list=repository.findItemsBySection(name);
		return list;		
	}

}
