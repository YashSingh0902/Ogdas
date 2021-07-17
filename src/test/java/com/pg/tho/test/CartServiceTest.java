package com.pg.tho.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pg.tho.entities.Address;
import com.pg.tho.entities.Category;
import com.pg.tho.entities.Customer;
import com.pg.tho.entities.FoodCart;
import com.pg.tho.entities.Item;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.ICartRepository;

class CartServiceTest {

	@Autowired
	ICartRepository repository;
	
	
	@Test
	void testIncreaseQuantity() {
		
		FoodCart cart=getCart();
		List<Item> list=cart.getItemList();
		int size1=list.size();
		Item item=new Item(5,"Fries",4,200.0,getCategory(),getSection());
		list.add(item);
		int size2=list.size();
		boolean r;
		if(size1<size2)
		{
			r=true;
		}
		else
		{
			r=false;
		}
		assertTrue(r);
	}

	@Test
	void testReduceQuantity() {
		
		FoodCart cart=getCart();
		List<Item> list=cart.getItemList();
		int size1=list.size();
		list.remove(0);
		int size2=list.size();
		boolean r;
		if(size1>size2)
		{
			r=true;
		}
		else
		{
			r=false;
		}
		assertTrue(r);
	}

	@Test
	void testRemoveItem() {

		FoodCart cart=getCart();
		List<Item> list=cart.getItemList();
		int size1=list.size();
		list.remove(0);
		int size2=list.size();
		boolean r;
		if(size1>size2)
		{
			r=true;
		}
		else
		{
			r=false;
		}
		assertTrue(r);
	}

	@Test
	void testClearCart() {
	}
	
	
	public FoodCart getCart()
	{
		FoodCart cart=new FoodCart();
		cart.setCartId(14);
		cart.setCustomer(getCustomer());
		List<Item> list=new ArrayList<Item>();
		list.add(getItem());
		cart.setItemList(list);
		return cart;
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
		sec.setSectionId(4);
		sec.setSectionName("Dairy");
		sec.setInChargeName("Aniket");
		return sec;
	}
	
	public Category getCategory()
	{
		Category cat = new Category();
		cat.setCatId(6);
		cat.setCategoryName("Veg");
		return cat;
	}
	
	public Item getItem()
	{
		Item item=new Item(1,"Paneer",2,200.0,getCategory(),getSection());
		return item;
	}

}
