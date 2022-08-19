package com.venkat.questionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class QuestionServiceBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceBootApplication.class, args);
	}
}