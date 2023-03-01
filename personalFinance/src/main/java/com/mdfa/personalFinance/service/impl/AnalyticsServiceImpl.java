package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.models.Income;
import com.mdfa.personalFinance.repository.ExpenseRepo;
import com.mdfa.personalFinance.repository.IncomeRepo;
import com.mdfa.personalFinance.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired IncomeRepo incomeRepo;
    @Autowired ExpenseRepo expenseRepo;
    @Override
    public int totalIncome() {
        List<Income> incomes = incomeRepo.findAll();
        AtomicInteger total = new AtomicInteger();
        incomes.forEach(income -> total.addAndGet(income.getAmount()));
        return total.get();
    }

    @Override
    public int totalExpenseByCategory(Category category) {
        List<Expense> expenses = expenseRepo.findAll();
        AtomicInteger total = new AtomicInteger();
        expenses.stream().filter(expense -> expense.getCategory().equals(category))
                .forEach(expense -> total.addAndGet(expense.getAmount()));
        return total.get();
    }
}
