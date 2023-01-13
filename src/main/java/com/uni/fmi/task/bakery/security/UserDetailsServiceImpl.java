package com.uni.fmi.task.bakery.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.fmi.task.bakery.user.Role;
import com.uni.fmi.task.bakery.user.User;
import com.uni.fmi.task.bakery.user.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Set<Role> roles = user.getRoles();
		
		return new UserDetailsImpl(user, roles);
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		UserEntity user = userRepository.findByUsername(username);
//		
//		if(user == null) {
//			throw new UsernameNotFoundException(
//					username + " was slacking....");
//		}
//		
//		Set<RoleEntity> roles = user.getRoles();
//		
//		return new UserPrincipal(user, roles);
//	}
}
