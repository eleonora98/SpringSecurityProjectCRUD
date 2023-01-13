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
	    Root<Product> quest = cq.from(Product.class);
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    if (search.getName() != null) {
	        predicates.add(cb.like(quest.get("name"), search.getName()));
	    }
	    if (search.getDescription() != null) {
	        predicates.add(cb.like(quest.get("description"), "%" + search.getDescription() + "%"));
	    }
	    cq.select(quest).where(predicates.toArray(new Predicate[] {}));
	    List<Product> products = em.createQuery(cq).getResultList();
	    System.out.print(products.size());
	    return products;
	}

}
