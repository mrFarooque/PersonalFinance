package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.models.Income;
import com.mdfa.personalFinance.repository.IncomeRepo;
import com.mdfa.personalFinance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncomeImpl implements IncomeService {
    @Autowired
    IncomeRepo incomeRepo;
    @Override
    public Income newIncome(Income income) {
        income.setDate(LocalDateTime.now());
        return incomeRepo.save(income);
    }
}
