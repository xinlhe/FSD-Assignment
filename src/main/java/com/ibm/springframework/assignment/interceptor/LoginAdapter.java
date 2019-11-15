package com.ibm.springframework.assignment.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginAdapter implements WebMvcConfigurer {

	@Bean
	LoginInterceptor LoginInterceptor() {

		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/tologin").excludePathPatterns("/defaultKaptcha")
				.excludePathPatterns("/static/**");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/css/").addResourceLocations("classpath:/images/");

	}

}
