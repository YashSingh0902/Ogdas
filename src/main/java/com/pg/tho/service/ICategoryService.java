package com.pg.tho.service;

import java.util.List;

import com.pg.tho.entities.Category;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.removeFailedException;

public interface ICategoryService {

	public Category addCategory(Category cat);
	public Category updateCategory(Category cat);
	public String removeCategory(Category cat) throws removeFailedException;
	public Category viewCategoryById(int id) throws IdNotFoundException;
	public List<Category> viewAllCategory();
	//public void updateName(Integer catId, String categoryName) throws Exception;

}
