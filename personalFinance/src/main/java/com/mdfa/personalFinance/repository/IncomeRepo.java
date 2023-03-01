package com.mdfa.personalFinance.repository;

import com.mdfa.personalFinance.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {
}
