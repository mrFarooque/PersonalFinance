package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.models.Income;

public interface IncomeService {
    Income newIncome(Income income);
    int totalIncome();
    int totalIncomeByMonth(int month);
    int balance(int month);
}
