package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense newExpense(Expense expense);
    int totalExpense();
    List<Expense> listAllExpense();
    int totalExpenseByCategory(Category category);
    int totalExpenseByCategoryAndMonth(Category category, int month);
    int totalExpenseByMonth(int month);
    List<Expense> listExpenseByMonth(int month);
}
