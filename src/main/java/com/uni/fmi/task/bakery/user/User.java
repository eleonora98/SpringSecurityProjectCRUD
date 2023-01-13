package com.uni.fmi.task.bakery.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.uni.fmi.task.bakery.constants.DbTables;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DbTables.TABLE_USERS)
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "username", nullable = false, length = 25, unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false, length = 64)
	private String password;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "avatar")
	private String avatar;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = DbTables.TABLE_USERS_ROLES, 
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	

	
}
