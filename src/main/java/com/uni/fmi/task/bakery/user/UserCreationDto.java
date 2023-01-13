package com.uni.fmi.task.bakery.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;
//	private MultipartFile avatar;

}
