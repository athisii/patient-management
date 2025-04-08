package com.athisii.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */


public record LoginRequestDto(
    @NotBlank(message = "Email is required")
    @Email
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String password
){}
