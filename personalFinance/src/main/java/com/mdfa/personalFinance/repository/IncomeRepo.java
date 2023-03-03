package com.mdfa.personalFinance.repository;

import com.mdfa.personalFinance.models.Expense;
import com.mdfa.personalFinance.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {
    @Query("select e from Income e where month(e.date) = ?1")
    List<Income> findAllByMonth(int month);
}
