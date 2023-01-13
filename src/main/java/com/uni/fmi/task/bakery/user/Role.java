package com.uni.fmi.task.bakery.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uni.fmi.task.bakery.constants.DbTables;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DbTables.TABLE_ROLES)
@Getter
@Setter
public class Role {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

//	@Getter
//	public static enum Roles {
//		ROLE_SYSTEM_ADMIN(1);
//
//		private Integer id = null;
//
//		private Roles(int roleId) {
//			this.id = roleId;
//		}
//	}
}

