package com.kh.project.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kh.project")
public class KhProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhProjectApplication.class, args);
	}

}
