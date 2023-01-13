package com.uni.fmi.task.bakery.category;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uni.fmi.task.bakery.constants.DbTables;
import com.uni.fmi.task.bakery.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DbTables.TABLE_CATEGORIES)
@Getter
@Setter
public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Product> products;

}
