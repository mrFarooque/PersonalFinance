package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Income;
import com.mdfa.personalFinance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired IncomeService incomeService;

    @PostMapping("/")
    public ResponseEntity<Income> newExpense(@RequestBody Income income) {
        income.setCategory(Category.SALARY);
        return new ResponseEntity<>(incomeService.newIncome(income), HttpStatus.ACCEPTED);
    }
    @GetMapping("/total")
    public ResponseEntity<Integer> totalIncome() {
        return new ResponseEntity<>(incomeService.totalIncome(), HttpStatus.OK);
    }

    @GetMapping("/total/month/current")
    public ResponseEntity<Integer> totalIncomeCurrentMonth() {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(incomeService.totalIncomeByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/balance/month/current")
    public ResponseEntity<Integer> balance() {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(incomeService.balance(month), HttpStatus.OK);
    }

}

