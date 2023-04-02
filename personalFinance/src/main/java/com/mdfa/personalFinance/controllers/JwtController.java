package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.config.CustomUserDetails;
import com.mdfa.personalFinance.helper.JwtUtil;
import com.mdfa.personalFinance.models.JwtRequest;
import com.mdfa.personalFinance.models.JwtResponse;
import com.mdfa.personalFinance.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getUsername(), jwtRequest.getPassword()));
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
