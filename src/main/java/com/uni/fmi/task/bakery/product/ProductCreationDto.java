package com.uni.fmi.task.bakery.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationDto {

	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Integer categoryId;
	private MultipartFile image;

}
