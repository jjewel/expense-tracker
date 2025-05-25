package com.jjewel.expense_tracker.service;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Override
    public List<Expense> getExpenseByDate(String date) {
        return ExpenseDataLoader.getExpenseList().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(date))
                .toList();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenseList().stream().filter(
                expense -> expense.getCategory().equalsIgnoreCase(category) && expense.getDate().startsWith(month)
        ).toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return ExpenseDataLoader.getExpenseList().stream()
                .map(Expense::getCategory) // shorthand for lambda expression (expense) -> expense.getCategory()
                .distinct().toList();
    }
}
