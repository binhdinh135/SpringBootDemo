package com.example.oneservicetwodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OneServiceTwoDbApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OneServiceTwoDbApplication.class, args);
	}

}
