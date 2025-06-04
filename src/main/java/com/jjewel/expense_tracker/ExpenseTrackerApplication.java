package com.jjewel.expense_tracker;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootApplication
public class ExpenseTrackerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@PostConstruct
	public void applicationLoaded() {
		System.out.println("------------------");
		System.out.println("Application Loaded");
		System.out.println("------------------");
	}

	@Override
	public void run(String... args) throws Exception {
		List<Expense> expenses = ExpenseDataLoader.getExpenseList();

		expenses.forEach(System.out::println);
		// System.out.println(expenses);
	}
}
