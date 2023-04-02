package com.mdfa.personalFinance.service;

import com.mdfa.personalFinance.models.User;

public interface UserService {
    User registerUser(User user);
    User findUserById(Integer id);
    User getUserFromContext();
}
