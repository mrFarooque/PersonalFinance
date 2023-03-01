package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.service.AnalyticsService;
import com.mdfa.personalFinance.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<Expense>> listAllExpense() {
        List<Expense> expenses = expenseService.listAllExpense();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalExpense() {
        return new ResponseEntity<>(expenseService.totalExpense(), HttpStatus.OK);
    }

    @GetMapping("/total/{category}")
    public ResponseEntity<Integer> totalByCategory(@PathVariable(value = "category") Category category) {
        return new ResponseEntity<>(analyticsService.totalExpenseByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/list/{month}")
    public ResponseEntity<List<Expense>> listByMonth(@PathVariable(value = "month") int month) {
        return new ResponseEntity<>(analyticsService.listExpenseByMonth(month), HttpStatus.OK);
    }

}
