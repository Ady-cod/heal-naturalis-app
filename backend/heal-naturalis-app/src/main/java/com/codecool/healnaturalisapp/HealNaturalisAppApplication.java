package com.codecool.healnaturalisapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealNaturalisAppApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty(Constants.DATABASE_URL, dotenv.get(Constants.DATABASE_URL));
		// Todo continue here
		System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
		System.setProperty("DATABASE_NAME", dotenv.get("DATABASE_NAME"));


		SpringApplication.run(HealNaturalisAppApplication.class, args);
	}

}
