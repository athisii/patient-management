package com.athisii.authservice.service.impl;

import com.athisii.authservice.entity.User;
import com.athisii.authservice.repository.UserRepository;
import com.athisii.authservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
