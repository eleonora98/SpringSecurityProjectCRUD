package com.uni.fmi.task.bakery.product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.uni.fmi.task.bakery.category.Category;
import com.uni.fmi.task.bakery.category.CategoryRepository;
import com.uni.fmi.task.bakery.exception.NoSuchEntityException;
import com.uni.fmi.task.bakery.util.FileUploadUtil;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	protected ResponseEntity<Product> createProduct(ProductCreationDto dto) throws IOException {
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
		if (!category.isPresent()) {
			throw new NoSuchEntityException(Category.class, dto.getCategoryId());
		}
		product.setCategory(category.get());
		if (dto.getImage() != null) {
			String fileName = StringUtils.cleanPath(dto.getImage().getOriginalFilename());
		    product.setImage(fileName);
		    String uploadDir = "/product-photos/" + product.getName();
		 
		    FileUploadUtil.saveFile(uploadDir, fileName, dto.getImage());
		}
		
		try {
			productRepository.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	protected ResponseEntity<Product> updateProduct(int id, ProductCreationDto dto) {
		Optional<Product> productData = productRepository.findById(id);
		if (!productData.isPresent()) {
			throw new NoSuchEntityException(Product.class, id);
		}
		Product product = productData.get();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
		if (!category.isPresent()) {
			throw new NoSuchEntityException(Category.class, dto.getCategoryId());
		}
		product.setCategory(category.get());
		
		try {
			productRepository.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	protected ResponseEntity<Product> getProduct(int id) {
		Optional<Product> productData = productRepository.findById(id);
		if (!productData.isPresent()) {
			throw new NoSuchEntityException(Product.class, id);
		}
		
		Product product = productData.get();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@Transactional
	protected ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productRepository.findAll();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}
	
	@Transactional
	protected ResponseEntity<Boolean> deleteProduct(int id) {
		Optional<Product> productData = productRepository.findById(id);
		if (!productData.isPresent()) {
			throw new NoSuchEntityException(Product.class, id);
		}
		
		Product product = productData.get();
		try {
			productRepository.delete(product);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
