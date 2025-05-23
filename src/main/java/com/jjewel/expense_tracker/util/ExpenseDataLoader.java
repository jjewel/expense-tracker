package com.jjewel.expense_tracker.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjewel.expense_tracker.model.Expense;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseDataLoader {

    private static List<Expense> expenseList = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = getClass().getResourceAsStream("/expenses.json");

        try {
            expenseList = mapper.readValue(stream, new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Expense> getExpenseList() {
        return expenseList;
    }
}
