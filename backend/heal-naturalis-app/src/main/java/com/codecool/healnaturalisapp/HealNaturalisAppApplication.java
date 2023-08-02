package com.codecool.healnaturalisapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealNaturalisAppApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty(Constants.DATABASE_URL, dotenv.get(Constants.DATABASE_URL));
		System.setProperty(Constants.DATABASE_USERNAME, dotenv.get(Constants.DATABASE_USERNAME));
		System.setProperty(Constants.DATABASE_PASSWORD, dotenv.get(Constants.DATABASE_PASSWORD));
		System.setProperty(Constants.DATABASE_NAME, dotenv.get(Constants.DATABASE_NAME));


		SpringApplication.run(HealNaturalisAppApplication.class, args);
	}

}
