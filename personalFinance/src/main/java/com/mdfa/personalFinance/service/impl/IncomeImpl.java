package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.models.Income;
import com.mdfa.personalFinance.repository.IncomeRepo;
import com.mdfa.personalFinance.service.ExpenseService;
import com.mdfa.personalFinance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class IncomeImpl implements IncomeService {
    @Autowired IncomeRepo incomeRepo;
    @Autowired ExpenseService expenseService;
    @Override
    public Income newIncome(Income income) {
        return incomeRepo.save(income);
    }

    @Override
    public int totalIncome() {
        List<Income> incomes = incomeRepo.findAll();
        AtomicInteger total = new AtomicInteger();
        incomes.forEach(income -> total.addAndGet(income.getAmount()));
        return total.get();
    }

    @Override
    public int totalIncomeByMonth(int month) {
        List<Income> incomes = incomeRepo.findAllByMonth(month);
        AtomicInteger total = new AtomicInteger();
        incomes.forEach(income -> total.addAndGet(income.getAmount()));
        return total.get();
    }

    @Override
    public int balance(int month) {
        return totalIncomeByMonth(month) - expenseService.totalExpenseByMonth(month);
    }

}
