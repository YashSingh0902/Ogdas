	package com.pg.tho.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.pg.tho.entities.Item;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.invalidNameException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.IItemService;
import com.pg.tho.service.ItemService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/item")
public class ItemController {
	
	@Autowired
	IItemService itemService;
	
	@Autowired
	private Environment environment;
	
	Logger logger=LoggerFactory.getLogger(ItemController.class);

	@PostMapping("/addItem")
	public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
		
		logger.info("ADD Item -> /./././");
		Item addItem = itemService.addItem(item);
		return new ResponseEntity<Item>(addItem, HttpStatus.OK);
	}

	@PutMapping("/updateItem")
	public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) throws IdNotFoundException {
		
		logger.info("UPDATE Item -> /./././");
		Item item1 = itemService.viewItemById(item.getItemId());
		if (item1 == null) {
			throw new IdNotFoundException("Invalid Item Id - Updation Failed (Retry)");
		} else {
			Item updateItem = itemService.updateItem(item);
			return new ResponseEntity<Item>(updateItem, HttpStatus.OK);
		}
	}
	
	/*@PutMapping(value = "updateItem/{itemId}")
	// @ApiOperation("Updates the item quantity by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateItem(@PathVariable Integer itemId, @RequestBody Item quantity) throws Exception {
		itemService.updateItem(itemId, quantity.getQuantity());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PutMapping(value = "updateCustomer/{itemId}")
	// @ApiOperation("Updates the item cost by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateCost(@PathVariable Integer itemId, @RequestBody Item cost) throws Exception {
		itemService.updateCost(itemId, cost.getCost());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}*/

	@DeleteMapping("/removeItemById/{itemId}")
	public ResponseEntity<String> removeItem(@PathVariable("itemId") int iid) throws removeFailedException {
		
		logger.info("REMOVE Item -> /./././");
		Item removeItem = itemService.viewItemById(iid);
		if (removeItem == null) {
			throw new removeFailedException("ItemId is Invalid - Try again with other");
		} else {
			String message = itemService.removeItem(removeItem);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
	}

	@GetMapping("/viewItemByItemId/{itemId}")
	public ResponseEntity<Item> viewItemById(@PathVariable("itemId") int iid) throws IdNotFoundException {
		
		logger.info("VIEW Item -> /./././");
		Item itemById = itemService.viewItemById(iid);
		return new ResponseEntity<Item>(itemById, HttpStatus.OK);

	}

	@GetMapping("/viewAllItemsByCategory/{categoryName}")
	public ResponseEntity<List<Item>> viewAllItemsByCategory(@PathVariable("categoryName") String name)
			throws IdNotFoundException {

		logger.info("VIEW Item (CATEGORY)  -> /./././");
		List<Item> viewByCategory = itemService.viewAllItemsByCategory(name);
		if (viewByCategory.isEmpty()) {
			throw new IdNotFoundException("Entered category is Invalid - Try again");
		}
		return new ResponseEntity<List<Item>>(viewByCategory, HttpStatus.OK);
	}

	@GetMapping("/viewAllItemsByItemName/{itemName}")
	public ResponseEntity<List<Item>> viewAllItemsByItemName(@PathVariable("itemName") String name)
			throws invalidNameException {
		
		logger.info("VIEW Item (NAME) -> /./././");
		List<Item> viewByName = itemService.viewAllItemsByItemName(name);
		if (viewByName.isEmpty()) {
			throw new invalidNameException("Entered name is Invalid - Try again");
		}
		return new ResponseEntity<List<Item>>(viewByName, HttpStatus.OK);
	}

	@GetMapping("/findItemsBySection/{sectionName}")
	public ResponseEntity<List<Item>> findItemsBySection(@PathVariable("sectionName") String name)
			throws invalidNameException {
		
		logger.info("FIND Item (SECTION) -> /./././");
		List<Item> items = itemService.findItemsBySections(name);
		if (items.isEmpty()) {
			throw new invalidNameException("Entered name is Invalid - Try again");
		}
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

}
