package com.uni.fmi.task.bakery.product;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> createProduct(@ModelAttribute @Valid ProductCreationDto dto)
			throws IOException {
		return productService.createProduct(dto);
	}

	@PutMapping("/{id}")
	public String updateProduct(@PathVariable("id") int id, @ModelAttribute ProductCreationDto dto) throws IOException {
		ResponseEntity<Product> product = productService.updateProduct(id, dto);
		
		if (product.getStatusCode().equals(HttpStatus.OK)) {
			return "admin-products.html";
		}
		return "error.html";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		return productService.getProduct(id);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Product>> getFilteredProducts(ProductSearchDto dto) {
		return productService.getFilteredProducts(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id) {
		return productService.deleteProduct(id);
	}

}
