package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.repository.ExpenseRepo;
import com.mdfa.personalFinance.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;

    @Override
    public Expense newExpense(Expense expense) {
        expense.setDate(LocalDate.now());
        return expenseRepo.save(expense);
    }

    @Override
    public int totalExpense() {
        AtomicInteger total = new AtomicInteger();
        expenseRepo.findAll().forEach(expense -> total.addAndGet(expense.getAmount()));
        return total.get();
    }

    @Override
    public List<Expense> listAllExpense() {
        return expenseRepo.findAll();
    }

}
