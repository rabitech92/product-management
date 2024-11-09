package com.products.controller;

import com.products.model.Users;
import com.products.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

@Autowired
 private UserService userService;


    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getUsers();
    }


    @PostMapping("/register")
    public Users createUser(@RequestBody Users user) {
       return userService.createUser(user);

    }
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        String token = userService.verify(user);
        if(token.equals("fail"))
            return "Invalid credentials";
        return token;
    }

    @GetMapping("/msg")
    public String welcomeUser(HttpServletRequest request) {
        return "Welcome to the Product Management Application! "+request.getSession().getId();
    }


}
