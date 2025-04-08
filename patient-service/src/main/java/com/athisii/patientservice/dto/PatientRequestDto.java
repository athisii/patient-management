package com.athisii.patientservice.dto;

import com.athisii.patientservice.dto.validators.CreatePatientValidationGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/4/25
 */

public record PatientRequestDto(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone is required")
        String phone,

        @NotNull(message = "Date of birth is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthDate,

        @NotBlank(message = "Address is required")
        String address,

        @NotNull(groups = {CreatePatientValidationGroup.class}, message = "Registered date is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate registeredDate) {
}
