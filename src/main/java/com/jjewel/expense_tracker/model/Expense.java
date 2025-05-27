package com.jjewel.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

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
