package com.pg.tho.service;

import java.util.List;

import com.pg.tho.entities.FoodCart;
import com.pg.tho.entities.Item;

public interface ICartService {

	public FoodCart addItemToCart(int cartId,int itemId);
	public List<Item> increaseQuantity(int cart_id,int item_id);
	public FoodCart reduceQuantity(int cart_id,int item_id);
	public String removeItem(FoodCart cart,Item item);
	public String clearCart(int cartId);
	public FoodCart getCartById(int cartId);
	public Item getItemById(int itemId);
	public int getCart(int custId);
	public List<Item> viewAllItems(int cartId);
		
}
