package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticsService {
    int totalIncome();
    int totalExpenseByCategory(Category category);
    List<Expense> listExpenseByMonth(int month);
}
