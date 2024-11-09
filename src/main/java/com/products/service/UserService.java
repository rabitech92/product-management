package com.products.service;

import com.products.model.Users;

import java.util.List;

public interface UserService {

    List<Users> getUsers();
    Users createUser(Users user);
    String verify(Users users);
}
