package com.jjewel.expense_tracker.service;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("h2")
public class ExpenseServiceH2Impl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpenseByDate(String date) {
        return List.of();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return List.of();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return List.of();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return Optional.empty();
    }

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        return false;
    }
}
