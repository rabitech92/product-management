package com.products.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    @GetMapping
    public String welcomeUser(HttpServletRequest request) {
        return "Welcome to the Product Management Application! "+request.getSession().getId()+" + "+ request.getSession().getAttribute("Rabiul");
    }
}
