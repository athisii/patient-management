package com.athisii.patientservice.dto;

import java.time.LocalDate;

/**
 * @author athisii
 * @version 1.0
 * @since 3/19/25 8:15 PM
 */

public record PatientResponseDto(Long id, String name, String email, String phone, LocalDate dateOfBirth, String address) {
}
