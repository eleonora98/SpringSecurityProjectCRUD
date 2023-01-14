package com.uni.fmi.task.bakery.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class ProductFinder {
	
	@PersistenceContext
	private EntityManager em;

	public List<Product> findProducts(ProductSearchDto search) {

	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Product> cq = cb.createQuery(Product.class);
	    Root<Product> product = cq.from(Product.class);
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    if (search.getName() != null) {
	        predicates.add(cb.like(product.get("name"), search.getName()));
	    }
	    if (search.getDescription() != null) {
	        predicates.add(cb.like(product.get("description"), "%" + search.getDescription() + "%"));
	    }
//	    if (search.getCategoryId() != null && search.getCategoryId() != "0") {
//	    	 Join<Product, Category> country = product.join("category");
//	        predicates.add(cb.like(country.get("id").as(String.class),  "%" + search.getCategoryId() + "%"));
//	    }
	    cq.select(product).where(predicates.toArray(new Predicate[] {}));
	    List<Product> products = em.createQuery(cq).getResultList();
	    System.out.print(products.size());
	    return products;
	}

}
