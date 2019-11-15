package com.ibm.springframework.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * ApplicationStarter is starter for SpringBoot Assignment.
 * 
 * Author : he xinlong
 *
 */
@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class ApplicationStarter extends SpringBootServletInitializer {
	
	private final static Logger log=LoggerFactory.getLogger(ApplicationStarter.class);

	public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

	/**
	 *新增此方法
	 */
	 @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	   return builder.sources(ApplicationStarter .class);
	}
}
