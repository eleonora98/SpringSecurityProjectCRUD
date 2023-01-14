package com.uni.fmi.task.bakery.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {
	
	@NotBlank
	private String username;
	@NotBlank
	private String firstName;
	private String lastName;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	private String repeatPassword;

}
