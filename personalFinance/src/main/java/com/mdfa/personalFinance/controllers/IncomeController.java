package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.models.Income;
import com.mdfa.personalFinance.service.AnalyticsService;
import com.mdfa.personalFinance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired IncomeService incomeService;
    @Autowired AnalyticsService analyticsService;

    @PostMapping("/")
    public ResponseEntity<Income> newExpense(@RequestBody Income income){
        incomeService.newIncome(income);
        return new ResponseEntity<>(income, HttpStatus.ACCEPTED);
    }
    @GetMapping("/")
    public ResponseEntity<Integer> totalIncome() {
        return new ResponseEntity<>(analyticsService.totalIncome(), HttpStatus.OK);
    }
}

