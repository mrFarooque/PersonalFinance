package com.mdfa.personalFinance.repository;

import com.mdfa.personalFinance.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

    @Query("select e from Expense e where month(e.date) = ?1")
    List<Expense> findAllByMonth(int month);

}
