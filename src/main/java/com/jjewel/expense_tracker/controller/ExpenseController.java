package com.jjewel.expense_tracker.controller;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories() {
        List<String> categories = ExpenseDataLoader
                .getExpenseList()
                .stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
