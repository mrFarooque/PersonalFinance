package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.helper.JwtUtil;
import com.mdfa.personalFinance.models.User;
import com.mdfa.personalFinance.repository.UserRepo;
import com.mdfa.personalFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User getUserFromContext() {
        String username = jwtUtil.extractUsername(jwtUtil.getTokenFormRequestContext());
        return userRepo.findByUsername(username);
    }

}
