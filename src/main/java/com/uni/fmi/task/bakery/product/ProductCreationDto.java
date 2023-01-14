package com.uni.fmi.task.bakery.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private Double price;
	@NotNull
	private Integer quantity;
	@NotNull
	private Integer categoryId;
	private MultipartFile image;

}
