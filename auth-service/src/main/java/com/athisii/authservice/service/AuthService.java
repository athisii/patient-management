package com.athisii.authservice.service;

import com.athisii.authservice.dto.LoginRequestDto;

import java.util.Optional;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */


public interface AuthService {
    Optional<String> authenticate(LoginRequestDto loginRequestDto);

    boolean validateToken(String token);
}
