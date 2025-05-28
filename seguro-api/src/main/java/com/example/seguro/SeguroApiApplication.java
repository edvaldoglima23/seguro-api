package com.example.seguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SeguroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguroApiApplication.class, args);
	}

} 