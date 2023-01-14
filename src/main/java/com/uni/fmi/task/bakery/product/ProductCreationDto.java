package com.uni.fmi.task.bakery.product;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationDto {

	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotBlank
	private Double price;
	@NotBlank
	private Integer quantity;
	@NotBlank
	private Integer categoryId;
	private MultipartFile image;

}
