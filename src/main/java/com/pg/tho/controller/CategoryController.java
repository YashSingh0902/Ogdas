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

import com.pg.tho.entities.Category;
import com.pg.tho.entities.Customer;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.CategoryService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	private Environment environment;
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);
	
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category)
	{ 
		logger.info("ADD Category  -> ././././");
		Category addCategory=categoryService.addCategory(category);
		return new ResponseEntity<Category>(addCategory,HttpStatus.OK);
		
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) throws IdNotFoundException
	{ 
		logger.info("UPDATE Category-> ././././");
		Category updateCategory=categoryService.viewCategoryById(category.getCatId());
		if(updateCategory==null)
		{
			throw new IdNotFoundException("Category not available");
		}
		else
		{
			Category updateCategory1=categoryService.updateCategory(category);
			return new ResponseEntity<Category>(updateCategory1,HttpStatus.OK);
		}
		
	}
	/*@PutMapping(value = "updateCategory/{catId}")
	// @ApiOperation("Updates the category name by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateName(@PathVariable Integer catId, @RequestBody Category name) throws Exception {
		categoryService.updateName(catId, name.getCategoryName());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}*/
	
	@DeleteMapping("/removeCategory/{categoryId}")
	public ResponseEntity<String> removeCategory(@PathVariable("categoryId") int catId) throws removeFailedException
	{ 
		logger.info("REMOVE Category  -> ././././");
		Category category1=categoryService.viewCategoryById(catId);
		System.err.println(category1);
		if(category1==null)
		{
			throw new removeFailedException("Entered Category is Invalid - Try again with other Category ");
		}
		else
		{
			String message=categoryService.removeCategory(category1);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
	}
	
	@GetMapping("/viewCategory/{categoryId}")
	public ResponseEntity<Category> viewCategory(@PathVariable("categoryId") int id) throws IdNotFoundException
	{
		logger.info("VIEW Category (ID) -> ././././");
		Category categoryById=categoryService.viewCategoryById(id);
		if(categoryById==null)
		{
			throw new IdNotFoundException("Invalid Category Id - Try again");
		}
		else
		{
			return new ResponseEntity<Category>(categoryById,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/viewAllCategory")
	public ResponseEntity<List<Category>> viewAllCategory()
	{ 
		logger.info("VIEW All Category -> ././././");
		List<Category> viewCategory=categoryService.viewAllCategory();
		return new ResponseEntity<List<Category>>(viewCategory,HttpStatus.OK);
		
	}
	
}
