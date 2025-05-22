package com.jjewel.expense_tracker;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@PostConstruct
	public void applicationLoaded() {
		System.out.println("------------------");
		System.out.println("Application Loaded");
		System.out.println("------------------");
	}
}
