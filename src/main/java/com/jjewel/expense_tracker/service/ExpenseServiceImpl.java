package com.jjewel.expense_tracker.service;

import com.jjewel.expense_tracker.model.Expense;
import com.jjewel.expense_tracker.util.ExpenseDataLoader;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private static final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<Expense> getExpenses() {
        List<Expense> expenses = ExpenseDataLoader.getExpenseList();
        expenses.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));

        return ExpenseDataLoader.getExpenseList();
    }

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

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return ExpenseDataLoader.getExpenseList().stream().filter(
                expense -> expense.getId().equals(id)
        ).findFirst();
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setId(idCounter.incrementAndGet());

        ExpenseDataLoader.getExpenseList().add(expense);

        return expense;
    }

    @Override
    public boolean updateExpense(Expense updatedExpense) {
        Optional<Expense> existingExpense = getExpenseById(updatedExpense.getId());
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenseList().remove(existingExpense.get());
            ExpenseDataLoader.getExpenseList().add(updatedExpense);

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> existingExpense = getExpenseById(id);

        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenseList().remove(existingExpense.get());
            return true;
        }

        return false;
    }
}
