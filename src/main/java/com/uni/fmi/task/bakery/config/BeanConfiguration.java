package com.uni.fmi.task.bakery.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfiguration implements WebMvcConfigurer {
	
	@Bean
	public static BCryptPasswordEncoder getBcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		exposeDirectory("product-photos", registry);
//	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/product-photos/**")
          .addResourceLocations("/product-photos/");	
    }

	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();

		if (dirName.startsWith("../"))
			dirName = dirName.replace("../", "");

		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}
}
