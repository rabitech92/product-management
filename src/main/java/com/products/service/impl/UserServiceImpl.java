package com.products.service.impl;

import com.products.model.Users;
import com.products.repository.UserRepository;
import com.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);



    @Override
    public List<Users> getUsers() {
         List<Users> user = userRepository.findAll();
         return user;
    }

    @Override
    public Users createUser(Users user) {
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
    }

    public String verify(Users users){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(users.getUsername());
        return "fail";
    }
}
