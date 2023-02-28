package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.repository.ExpenseRepo;
import com.mdfa.personalFinance.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;

    @Override
    public Expense newExpense(Expense expense) {
        expense.setDate(LocalDateTime.now());
        return expenseRepo.save(expense);
    }
}
