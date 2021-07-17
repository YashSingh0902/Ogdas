package com.pg.tho.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pg.tho.entities.Category;
import com.pg.tho.repositories.ICategoryRepository;

@Service
@Transactional
public class CategoryService implements ICategoryService {
	
	@Autowired
    ICategoryRepository repository;
	
	Logger logger=LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public Category addCategory(Category cat) {
		
		logger.info("ADD Category  -> /./././ ");
		repository.save(cat);
		return cat;
	}

	@Override
	public Category updateCategory(Category cat) {
		
		logger.info("UPDATE Category  -> /./././ ");
		repository.save(cat);
		return cat;
	}

	/*@Override
	public void updateName(Integer catId, String categoryName) throws Exception {
		// TODO Auto-generated method stub
		Optional<Category> updateName = repository.findById(catId);
		Category name = updateName.orElseThrow(() -> new Exception("Service.CATEGORY_NOT_FOUND"));
		name.setCategoryName(categoryName);
		
	}*/
	
	@Override
	public String removeCategory(Category cat) {
		
		logger.info("REMOVE Category  -> /./././");
		repository.delete(cat);
		String message="Removal of Category => DONE";
		return message;
	}

	@Override
	public Category viewCategoryById(int id) {
		
		logger.info("VIEW Category  -> /./././");
		Category categoryID=repository.findById(id).orElse(null);
		return categoryID;
	}

	@Override
	public List<Category> viewAllCategory() {
		
		logger.info("VIEW ALL Category  -> /./././");
		List<Category> allCategory=repository.findAll();
		return allCategory;
	}

}
