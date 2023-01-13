package com.uni.fmi.task.bakery.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.fmi.task.bakery.exception.ApplicationException;
import com.uni.fmi.task.bakery.exception.NoSuchEntityException;
import com.uni.fmi.task.bakery.product.Product;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	protected ResponseEntity<Category> createCategory(String name) {
		Optional<Category> categoryData = categoryRepository.findByName(name);
		if (categoryData.isPresent()) {
			throw new ApplicationException("Category already exists");
		}
		Category category = new Category();
		category.setName(name);
		
		try {
			categoryRepository.save(category);
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	protected ResponseEntity<Category> updateCategory(int id, String name) {
		Optional<Category> categoryData = categoryRepository.findById(id);
		if (!categoryData.isPresent()) {
			throw new NoSuchEntityException(Category.class, id);
		}
		Category category = categoryData.get();
		category.setName(name);

		categoryRepository.save(category);
		
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@Transactional
	protected ResponseEntity<Category> getCategory(int id) {
		Optional<Category> categoryData = categoryRepository.findById(id);
		if (!categoryData.isPresent()) {
			throw new NoSuchEntityException(Product.class, id);
		}
		
		Category category = categoryData.get();
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@Transactional
	protected ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);

	}
	
	@Transactional
	protected ResponseEntity<Boolean> deleteCategory(int id) {
		Optional<Category> categoryData = categoryRepository.findById(id);
		if (!categoryData.isPresent()) {
			throw new NoSuchEntityException(Product.class, id);
		}
		
		Category category = categoryData.get();
		try {
			categoryRepository.delete(category);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
