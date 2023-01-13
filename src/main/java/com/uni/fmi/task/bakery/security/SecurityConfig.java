package com.uni.fmi.task.bakery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.uni.fmi.task.bakery.config.BeanConfiguration;

@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(BeanConfiguration.getBcryptPasswordEncoder());     
	}
	@Bean
	public HttpFirewall allowSemicolonHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedDoubleSlash(true);
	    return firewall;
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	  super.configure(web);
//	  web.httpFirewall(allowSemicolonHttpFirewall());
//	}
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.authorizeRequests()
		// public
			.antMatchers( "/", "/login", "/logout", "/login-page", "/current-user", "/register",
					"/sign-in-up-form.html", "/shop.html", "/index.html",
					"/about.html", "/single-product.html", "/product-photos").permitAll()
			.antMatchers("/assets/**").permitAll()
			.antMatchers(HttpMethod.GET, "/products/**", "/categories/**").permitAll()
			.antMatchers("/create-product", "/update-product", "/admin", 
					"/categories-page").hasAnyAuthority("ROLE_ADMIN")

			//post requests
			.antMatchers(HttpMethod.POST, "/products", "/categories/add")
				.hasAnyAuthority("ROLE_ADMIN")
			//put requests
			.antMatchers(HttpMethod.PUT, "/products/{id}", "/categories/{id}")
				.hasAnyAuthority("ROLE_ADMIN")
			//delete requests
			.antMatchers(HttpMethod.DELETE, "/products/{id}", "/categories/{id}")
				.hasAnyAuthority("ROLE_ADMIN")
				
			.anyRequest().authenticated()
			.and().csrf().disable();
    	
	        return httpSecurity.build();
    }

	public UserDetailsService userDetailService() {
		return userDetailsServiceImpl; 
	}

}
