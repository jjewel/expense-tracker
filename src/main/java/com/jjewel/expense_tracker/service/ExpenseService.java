package com.jjewel.expense_tracker.service;

import com.jjewel.expense_tracker.model.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getExpenses();
    List<Expense> getExpenseByDate(String date);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month);
    List<String> getAllExpenseCategories();
    Optional<Expense> getExpenseById(Long id);
    Expense addExpense(Expense expense);
    boolean updateExpense(Expense expense);
    boolean deleteExpense(Long id);


}
