package com.athisii.authservice.service.impl;

import com.athisii.authservice.dto.LoginRequestDto;
import com.athisii.authservice.service.AuthService;
import com.athisii.authservice.service.UserService;
import com.athisii.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */

@Service
public class AuthServiceImpl implements AuthService {

    final UserService userService;
    final PasswordEncoder passwordEncoder;
    final JwtUtil jwtUtil;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        return userService
                .findByEmail(loginRequestDto.email())
                .filter(u -> passwordEncoder.matches(loginRequestDto.password(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
