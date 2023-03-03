package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.enums.Category;
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

    @Override
    public int totalExpenseByCategory(Category category) {
        List<Expense> expenses = expenseRepo.findAll();
        AtomicInteger total = new AtomicInteger();
        expenses.stream().filter(expense -> expense.getCategory().equals(category))
                .forEach(expense -> total.addAndGet(expense.getAmount()));
        return total.get();
    }

    @Override
    public int totalExpenseByCategoryAndMonth(Category category, int month) {
        List<Expense> expenses = expenseRepo.findAllByMonth(month);
        AtomicInteger total = new AtomicInteger();
        expenses.stream()
                .filter(expense -> expense.getCategory().equals(category))
                .forEach(expense -> total.addAndGet(expense.getAmount()));
        return total.get();
    }

    @Override
    public int totalExpenseByMonth(int month) {
        List<Expense> expenses = expenseRepo.findAllByMonth(month);
        AtomicInteger total = new AtomicInteger();
        expenses.stream().forEach(expense -> total.addAndGet(expense.getAmount()));
        return total.get();
    }

    @Override
    public List<Expense> listExpenseByMonth(int month) {
        return expenseRepo.findAllByMonth(month);
    }

}
