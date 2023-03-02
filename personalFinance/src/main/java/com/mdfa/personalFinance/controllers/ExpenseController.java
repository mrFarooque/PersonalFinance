package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;
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

    @PostMapping("/")
    public ResponseEntity<Expense> newExpense(@RequestBody Expense expense) {
        expenseService.newExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Expense>> listAllExpense() {
        List<Expense> expenses = expenseService.listAllExpense();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/list/{month}")
    public ResponseEntity<List<Expense>> listByMonth(@PathVariable(value = "month") int month) {
        return new ResponseEntity<>(expenseService.listExpenseByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/list/current")
    public ResponseEntity<List<Expense>> listByCurrentMonth() {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(expenseService.listExpenseByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalExpense() {
        return new ResponseEntity<>(expenseService.totalExpense(), HttpStatus.OK);
    }

    @GetMapping("/total/category/{category}")
    public ResponseEntity<Integer> totalByCategory(@PathVariable(value = "category") Category category) {
        return new ResponseEntity<>(expenseService.totalExpenseByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/total/month/{month}")
    public ResponseEntity<Integer> totalByMonth(@PathVariable(value = "month") int month) {
        return new ResponseEntity<>(expenseService.totalExpenseByMonth(month), HttpStatus.OK);
    }

}
