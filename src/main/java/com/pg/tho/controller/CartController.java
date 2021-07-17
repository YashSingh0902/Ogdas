package com.pg.tho.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.FoodCart;
import com.pg.tho.entities.Item;
import com.pg.tho.exceptions.distinctSectionException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.ICartService;
import com.pg.tho.service.IItemService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	ICartService cartService;
	
	@Autowired
	IItemService itemService;
	
	@PostMapping("/addItemToCart/{cartId}/{itemId}")
	public ResponseEntity<FoodCart> addItemToCart(@PathVariable int cartId,@PathVariable int itemId) throws distinctSectionException{
		
		FoodCart cart1 = cartService.addItemToCart(cartId,itemId);
		if(cart1==null)
		{
			throw new distinctSectionException("Item unavailable - Invalid Id");
		}
		FoodCart cart2=new FoodCart();
		cart2.setCartId(cart1.getCartId());
		cart2.setCustomer(cart1.getCustomer());
		return new ResponseEntity<FoodCart>(cart2, HttpStatus.OK);
		
	}
	
	@PutMapping("/increaseQuantity/{cartId}/{itemId}")
	public ResponseEntity<List<Item>> increaseQuantity(@PathVariable("cartId") int cart_id,@PathVariable("itemId") int item_id){
		
		List<Item> list=cartService.increaseQuantity(cart_id,item_id);
		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/reduceQuantity/{cartId}/{itemId}")
	public ResponseEntity<FoodCart>  reduceQuantity(@PathVariable("cartId") int cart_id,@PathVariable("itemId") int item_id) throws removeFailedException{
		
		FoodCart cart=cartService.reduceQuantity(cart_id,item_id);
		
		if(cart==null)
		{
			throw new removeFailedException("Zero quantity. Add the items and Retry");
		}
		return new ResponseEntity<FoodCart>(cart, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeItem/{cartId}/{itemId}")
	public ResponseEntity<String> removeItem(@PathVariable int cartId, @PathVariable int itemId) throws removeFailedException{
		
		FoodCart cart=cartService.getCartById(cartId);
		if(cart==null)										
		{
			throw new removeFailedException("The cart is Empty. Add the items and Retry");
		}
		else
		{
			Item item=itemService.viewItemById(itemId);
			String message=cartService.removeItem(cart, item);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}	
	}
	
	@DeleteMapping("/clearCart/{cartId}")
	public ResponseEntity<String>  clearCart(@PathVariable int cartId){
		
		String message=cartService.clearCart(cartId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/getCart/{custId}")
	public ResponseEntity<Integer>  getCart(@PathVariable int custId){
		
		int cartId=cartService.getCart(custId);
		return new ResponseEntity<Integer>(cartId, HttpStatus.OK);
	}
	
	@GetMapping("/viewAllItems/{cartId}")
	public ResponseEntity<List<Item>>  viewAllItems(@PathVariable int cartId){
		
		List<Item> list=cartService.viewAllItems(cartId);
		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}
	
}
