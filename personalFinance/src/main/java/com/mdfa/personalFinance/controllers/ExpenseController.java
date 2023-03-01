package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.service.AnalyticsService;
import com.mdfa.personalFinance.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired ExpenseService expenseService;
    @Autowired AnalyticsService analyticsService;

    @PostMapping("/")
    public ResponseEntity<Expense> newExpense(@RequestBody Expense expense) {
        expenseService.newExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{category}")
    public ResponseEntity<Integer> totalByCategory(@PathVariable(value = "category") Category category) {
//        System.out.println(Category.valueOf(category));
//        return new ResponseEntity<>(analyticsService.totalExpenseByCategory(Category.valueOf(category)), HttpStatus.OK);
        return new ResponseEntity<>(analyticsService.totalExpenseByCategory(category), HttpStatus.OK);
    }
}
