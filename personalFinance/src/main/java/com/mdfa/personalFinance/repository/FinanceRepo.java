package com.mdfa.personalFinance.repository;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.enums.Type;
import com.mdfa.personalFinance.models.Finance;
import com.mdfa.personalFinance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinanceRepo extends JpaRepository<Finance, Integer> {
    @Query("select e from Finance e where month(e.date) = ?1 AND e.user= ?2")
    List<Finance> findAllByMonth(int month, User user);

    @Query("select e from Finance e where month(e.date) = ?1 AND e.type = ?2 AND e.user= ?3")
    List<Finance> findAllByMonthAndType(int month, Type type, User user);

    @Query("select e from Finance e where e.category = ?1 AND month(e.date) = ?2 AND e.user= ?3")
    List<Finance> findAllByCategoryAndMonth(Category category, int month, User user);

    @Query("select e from Finance e where e.category = ?1 AND e.user= ?2")
    List<Finance> findAllByCategory(Category category, User user);

    @Query("select e from Finance e where e.type = ?1 AND e.category = ?2 AND e.user= ?3")
    List<Finance> findAllByTypeAndCategory(Type type, Category category, User user);

    @Query("select e from Finance e where e.type = ?1 AND e.category = ?2 AND month(e.date) = ?3 AND e.user= ?4")
    List<Finance> findAllByTypeAndCategoryAndMonth(Type type, Category category, int month, User user);

    @Query("select e from Finance e where e.type = ?1 AND e.user = ?2")
    List<Finance> findAllByType(Type type, User user);

}
