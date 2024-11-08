package com.products.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String username);

    String extractUsername(String token);

    boolean validToken(String token, UserDetails details);
}
