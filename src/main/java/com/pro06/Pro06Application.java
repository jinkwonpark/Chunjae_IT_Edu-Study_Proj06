package com.pro06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Pro06Application {

	public static void main(String[] args) {
		SpringApplication.run(Pro06Application.class, args);
	}

}
