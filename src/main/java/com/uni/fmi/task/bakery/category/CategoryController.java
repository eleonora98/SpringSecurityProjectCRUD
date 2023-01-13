package com.uni.fmi.task.bakery.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestParam(value = "name") String name) {
		return categoryService.createCategory(name);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateProduct(@PathVariable("id") int id, @RequestParam("categoryName") String name) {
		return categoryService.updateCategory(id, name);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getProduct(@PathVariable("id") int id) {
		return categoryService.getCategory(id);
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllProducts() {
		return categoryService.getAllCategories();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id) {
		return categoryService.deleteCategory(id);
	}

}
