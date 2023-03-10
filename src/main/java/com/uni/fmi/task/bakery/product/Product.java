package com.uni.fmi.task.bakery.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.uni.fmi.task.bakery.category.Category;
import com.uni.fmi.task.bakery.constants.DbTables;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DbTables.TABLE_PRODUCTS)
@Getter
@Setter
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")

	private Category category;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "image", nullable = false)
	private String image;
	
	@Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
         
        return "product-photos/" + name.hashCode() + "/" + image;
    }

}
