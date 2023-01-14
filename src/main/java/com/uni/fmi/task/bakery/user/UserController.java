package com.uni.fmi.task.bakery.user;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostConstruct
	public void addFirstUser(){
	   userService.createAdmin();
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@ModelAttribute @Valid UserCreationDto dto, HttpSession session) throws Exception {
		User user = userService.registerUser(dto);
		if (user != null) {
			session.setAttribute("loggedUser", user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam(value = "username") String username,
						@RequestParam(value = "password") String password,
						HttpSession session) {

		User user = userService.login(username, password, session);
		
		if (user != null) {
			for (Role role : user.getRoles()) {
				if (role.getName().equals("ROLE_USER")) {
					return "index.html";
				} else if (role.getName().equals("ROLE_ADMIN")) {
					return "/admin";
				}
			}
			return "error.html";
		} else {
			return "error.html";
		}
	}
	
	@GetMapping("/current-user")
	public ResponseEntity<User> loggedUserId(HttpSession session){
		
		User user = (User)session.getAttribute("loggedUser");
		
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);			
		}else {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(HttpSession session){
		
		User user = (User)session.getAttribute("loggedUser");
		
		if(user != null) {
			session.invalidate();
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	}

}
