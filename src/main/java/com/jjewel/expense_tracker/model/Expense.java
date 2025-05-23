package com.jjewel.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Expense {

    @JsonProperty("id")
    private int id;

    @JsonProperty("expenseType")
    private boolean expenseType;

    @JsonProperty("date")
    private String date;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("category")
    private String category;

    @JsonProperty("account")
    private String account;

    @JsonProperty("note")
    private String note;
}
