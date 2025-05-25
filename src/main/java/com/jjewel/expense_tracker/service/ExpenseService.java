package com.jjewel.expense_tracker.service;

import com.jjewel.expense_tracker.model.Expense;
import java.util.List;

public interface ExpenseService {
    List<Expense> getExpenseByDate(String date);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month);
    List<String> getAllExpenseCategories();

}
