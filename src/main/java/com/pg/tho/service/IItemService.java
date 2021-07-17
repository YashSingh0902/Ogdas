package com.pg.tho.service;

import java.util.List;

import javax.validation.Valid;

import com.pg.tho.entities.Item;


public interface IItemService {

	public Item addItem(Item item);
	public Item viewItemById(int id);
	public Item updateItem(Item item);
	public String removeItem(Item item);
	public List<Item> findItemsBySections(String name);
	public List<Item> viewAllItemsByItemName(String name);
	public List<Item> viewAllItemsByCategory(String name);
	//public void updateItem(Integer itemId, int quantity) throws Exception;
	//public void updateCost(Integer itemId, double cost) throws Exception;
	
}
