package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.models.User;
import com.mdfa.personalFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/name")
    public ResponseEntity<User> getUserFromContext() {
        return new ResponseEntity<>(userService.getUserFromContext(), HttpStatus.OK);
    }

}
