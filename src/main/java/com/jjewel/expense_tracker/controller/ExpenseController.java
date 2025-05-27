package com.jjewel.expense_tracker.controller;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.service.ExpenseService;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(@Qualifier("h2") ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(expenseService.getExpenses());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories() {
        List<String> categories = expenseService.getAllExpenseCategories();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Expense>> getExpenseByDate(@PathVariable String date) {
        return ResponseEntity.ok(expenseService.getExpenseByDate(date));
    }

    /**
     * @param month example: month?month=11
     */
    @GetMapping("/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndMonth(
            @PathVariable String category,
            @RequestParam String month
    ) {
        return ResponseEntity.ok(expenseService.getExpenseByCategoryAndMonth(category, month));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense newExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id, @RequestBody Expense expense
    ) {
        expense.setId(id);
        boolean isUpdated = expenseService.updateExpense(expense);

        if (isUpdated) {
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        boolean isDeleted = expenseService.deleteExpense(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
