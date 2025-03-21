package com._Systems.calorietracker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalorietrackerApplication {

	public static void main(String[] args) {

		Dotenv env = Dotenv.configure().directory("./").ignoreIfMissing().load();

		env.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(CalorietrackerApplication.class, args);
	}

}
