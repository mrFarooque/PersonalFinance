package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.enums.Category;

public interface AnalyticsService {
    int totalIncome();
    int totalExpenseByCategory(Category category);
}
