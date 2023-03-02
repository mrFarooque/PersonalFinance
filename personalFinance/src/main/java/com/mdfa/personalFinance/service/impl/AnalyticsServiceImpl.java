package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.repository.ExpenseRepo;
import com.mdfa.personalFinance.repository.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsServiceImpl {

    @Autowired IncomeRepo incomeRepo;
    @Autowired ExpenseRepo expenseRepo;




}
