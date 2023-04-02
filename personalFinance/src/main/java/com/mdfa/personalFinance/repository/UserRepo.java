package com.mdfa.personalFinance.repository;

import com.mdfa.personalFinance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
