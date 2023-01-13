package com.uni.fmi.task.bakery.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
	
	@GetMapping
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index.html");
		return model;
	}
	
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin-page.html");
		return model;
	}
	
	@GetMapping(value = "/login-page")
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("sign-in-up-form.html");
		return model;
	}
	
	@GetMapping(value = "/create-product")
	public ModelAndView createProductPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("create-product.html");
		return model;
	}
	
	@GetMapping(value = "/update-product")
	public ModelAndView updateProductPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("update-product.html");
		return model;
	}
	
	@GetMapping(value = "/categories-page")
	public ModelAndView categoriesPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("categories.html");
		return model;
	}
	
	@GetMapping(value = "/products-page")
	public ModelAndView adminProductsPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin-products.html");
		return model;
	}

}
