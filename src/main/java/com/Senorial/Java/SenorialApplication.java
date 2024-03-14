package com.Senorial.Java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Senorial")
public class SenorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenorialApplication.class, args);
	}

}
