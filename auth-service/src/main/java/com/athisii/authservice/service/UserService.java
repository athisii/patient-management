package com.athisii.authservice.service;

import com.athisii.authservice.entity.User;

import java.util.Optional;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */


public interface UserService {
    Optional<User> findByEmail(String email);
}
