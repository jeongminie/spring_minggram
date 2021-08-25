package com.jeongmini.minggram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") //내가 url로 접근하고 싶은 path
		.addResourceLocations("file:///C:\\Users\\opooi\\OneDrive\\바탕 화면\\workspace\\spring_test\\upload\\minggram\\images/");
 	}

}
