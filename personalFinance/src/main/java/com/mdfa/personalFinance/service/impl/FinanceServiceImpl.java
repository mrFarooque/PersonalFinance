package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.enums.Type;
import com.mdfa.personalFinance.models.Finance;
import com.mdfa.personalFinance.repository.FinanceRepo;
import com.mdfa.personalFinance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    FinanceRepo financeRepo;

    @Override
    public Finance newFinance(Finance finance) {
        return financeRepo.save(finance);
    }

    @Override
    public void deleteFinanceById(int id) {
        financeRepo.deleteById(id);
    }

    @Override
    public List<Finance> listAll() {
        return financeRepo.findAll();
    }

    @Override
    public List<Finance> listAllByType(Type type) {
        return financeRepo.findAllByType(type);
    }

    @Override
    public List<Finance> listAllByMonth(int month) {
        return financeRepo.findAllByMonth(month);
    }

    @Override
    public List<Finance> listTypeByMonth(Type type, int month) {
        return financeRepo.findAllByMonthAndType(month, type);
    }

    @Override
    public int totalByType(Type type) {
        List<Finance> finances = financeRepo.findAllByType(type);
        AtomicInteger total = new AtomicInteger();
        finances.forEach(finance -> total.addAndGet(finance.getAmount()));
        return total.get();
    }

    @Override
    public int totalByTypeAndCategory(Type type, Category category) {
        List<Finance> finances = financeRepo.findAllByTypeAndCategory(type, category);
        AtomicInteger total = new AtomicInteger();
        finances.forEach(finance -> total.addAndGet(finance.getAmount()));
        return total.get();
    }

    @Override
    public int totalByCategoryAndMonth(Category category, int month) {
        List<Finance> finances = financeRepo.findAllByCategoryAndMonth(category, month);
        AtomicInteger total = new AtomicInteger();
        finances.forEach(finance -> total.addAndGet(finance.getAmount()));
        return total.get();
    }

    @Override
    public int totalByTypeAndMonth(Type type, int month) {
        List<Finance> finances = financeRepo.findAllByMonthAndType(month, type);
        AtomicInteger total = new AtomicInteger();
        finances.forEach(finance -> total.addAndGet(finance.getAmount()));
        return total.get();
    }

    @Override
    public int totalByTypeAndCategoryAndMonth(Type type, Category category, int month) {
        List<Finance> finances = financeRepo.findAllByTypeAndCategoryAndMonth(type, category, month);
        AtomicInteger total = new AtomicInteger();
        finances.forEach(finance -> total.addAndGet(finance.getAmount()));
        return total.get();
    }

    @Override
    public int balanceByMonth(int month) {
        return totalByTypeAndMonth(Type.INCOME, month) - totalByTypeAndMonth(Type.EXPENSE, month);
    }
}
