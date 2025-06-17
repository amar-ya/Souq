package com.example.Souq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SouqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SouqApplication.class, args);
	}

}