package com.pg.tho.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pg.tho.entities.Address;
import com.pg.tho.entities.Category;
import com.pg.tho.entities.Item;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.IItemRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ItemServiceTest {
	
	@Autowired
	IItemRepository repository;


//
//	@Test
//	void testRemoveItem() {
//		
//		repository.deleteById(15);
//	}

	@Test
	void testFindItemsBySection() {
		List<Item> list=repository.findItemsBySection("Pulses");
		assertNotNull(list);
	}

	@Test
	void testViewAllItemsByCategory() {
	
		List<Item> list = repository.findItemsByCategory("Pulse");
		assertNotNull(list);

	}

	@Test
	void testViewAllItemsByItemName() {
		List<Item> list=repository.findItemsByItemName("Uraddal");
		assertNotNull(list);
	}

//	@Test
//	void testViewItemById() {
//		Item item=repository.findById(15).orElse(null);
//		assertNotNull(item);
//	}

	public Item getItem()
	{
		Item item = new Item(); 
		item.setSection(getSection());
		item.setItemId(22);
		item.setItemName("Mooper");
		item.setQuantity(1);
		item.setCategory(getCategory());
		item.setCost(250);
		return item;
		
	}
		
		public Section getSection()
		{
			Section sec=new Section();
			sec.setSectionId(4);
			sec.setSectionName("HouseHold");
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
}
