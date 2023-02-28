package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/expense")
public class expenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/")
    public ResponseEntity<Expense> newExpense(@RequestBody Expense expense){
        expenseService.newExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }
}
