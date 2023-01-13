package com.uni.fmi.task.bakery.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uni.fmi.task.bakery.user.Role;
import com.uni.fmi.task.bakery.user.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -7808514016934172534L;

	private User user;
	private Set<GrantedAuthority> authorities;

	public UserDetailsImpl(User user, Set<Role> roles) {
		this.user = user;
		authorities = new HashSet<GrantedAuthority>();
		insertRoles(roles);
	}

	private void insertRoles(Set<Role> roles) {
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		if (authorities.isEmpty()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
