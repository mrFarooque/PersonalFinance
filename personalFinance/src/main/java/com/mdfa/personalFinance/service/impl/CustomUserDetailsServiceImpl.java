package com.mdfa.personalFinance.service.impl;

import com.mdfa.personalFinance.models.User;
import com.mdfa.personalFinance.repository.UserRepo;
import com.mdfa.personalFinance.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.mdfa.personalFinance.config.CustomUserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user does not exits");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
