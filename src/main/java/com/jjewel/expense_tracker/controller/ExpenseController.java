package com.jjewel.expense_tracker.controller;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.service.ExpenseService;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories() {
        List<String> categories = expenseService.getAllExpenseCategories();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/expenses/date/{date}")
    public ResponseEntity<List<Expense>> getExpenseByDate(@PathVariable String date) {
        return ResponseEntity.ok(expenseService.getExpenseByDate(date));
    }

    /**
     * @param category
     * @param month example: month?month=11
     * @return
     */
    @GetMapping("/expenses/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndMonth(
            @PathVariable String category,
            @RequestParam String month
    ) {
        return ResponseEntity.ok(expenseService.getExpenseByCategoryAndMonth(category, month));
    }

}
