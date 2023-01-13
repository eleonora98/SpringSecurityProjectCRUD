package com.uni.fmi.task.bakery.user;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.uni.fmi.task.bakery.security.SecurityConfig;
import com.uni.fmi.task.bakery.util.FileUploadUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	public void createAdmin() {
		User user = new User();
	    user.setEmail("admin@test.com");
	    user.setUsername("admin");
	    user.setFirstName("Eleonora");
	    user.setLastName("Peneva");
	    user.setPassword(passwordEncoder.encode("admin_"));
	    Set<Role> roles = new HashSet<Role>();
	    roles.add(roleRepository.findById(1).get());
	    user.setRoles(roles);
	    if (userRepository.findByUsername(user.getUsername()) != null) {
	    	return;
	    }
	    
	    userRepository.save(user);
	}
	
	public User registerUser(UserCreationDto dto) throws Exception {
		if(dto.getUsername().isEmpty()  || 
				dto.getEmail().isEmpty() 	||
				dto.getPassword().isEmpty()  || 
				!dto.getPassword().equals(dto.getRepeatPassword())) {
			return null;
		}
		
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		Set<Role> roles = new HashSet<Role>();
	    roles.add(roleRepository.findById(2).get());
	    user.setRoles(roles);
	    
	    if (userRepository.findByUsername(user.getUsername()) != null) {
	    	throw new Exception("User with same username already exists");
	    }
		
		return userRepository.saveAndFlush(user);	
	}
	
	public User login(String username, String password, 
									HttpSession session) {		
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		
		Boolean matches = passwordEncoder.matches(password, user.getPassword());
		System.out.println(matches);
		if(matches) {
			session.setAttribute("loggedUser", user);
			UserDetails userDetails = securityConfig.userDetailService()
					.loadUserByUsername(username);
			
			if(userDetails != null) {
				Authentication auth = 
						new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(),
								userDetails.getPassword(),
								userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
				ServletRequestAttributes attr = 
						(ServletRequestAttributes)RequestContextHolder
						.currentRequestAttributes();
				
				session.setAttribute("SPRING_SECURITY_CONTEXT"
						, SecurityContextHolder.getContext());				
			}	
		}
		return user;
	}

}
