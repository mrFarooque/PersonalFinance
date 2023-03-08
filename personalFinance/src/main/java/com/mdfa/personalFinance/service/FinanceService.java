package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.enums.Type;
import com.mdfa.personalFinance.models.Finance;
import java.util.List;

public interface FinanceService {
    Finance newFinance(Finance finance);
    Finance editFinanceById(Finance finance);
    void deleteFinanceById(int id);

    List<Finance> listAll();
    List<Finance> listAllByType(Type type);
    List<Finance> listAllByMonth(int month);
    List<Finance> listTypeByMonth(Type type, int month);
    int totalByType(Type type);
    int totalByTypeAndCategory(Type type, Category category);
    int totalByCategoryAndMonth(Category category, int month);
    int totalByTypeAndMonth(Type type, int month);
    int totalByTypeAndCategoryAndMonth(Type type,Category category, int month);

    // methods related to analytics
    int balanceByMonth(int month);
}
