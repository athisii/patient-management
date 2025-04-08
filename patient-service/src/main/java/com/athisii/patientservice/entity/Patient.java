package com.athisii.patientservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author athisii
 * @version 1.0
 * @since 3/19/25 7:49 PM
 */

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String name;

    @NotNull
    @Email
    @Column(unique = true)
    String email;

    @NotNull
    String address;

    @NotNull
    String phone;

    @NotNull
    LocalDate birthDate;

    @NotNull
    @Column(updatable = false)
    LocalDate registeredDate;

    @PrePersist
    public void prePersist() {
        if (registeredDate == null) {
            registeredDate = LocalDate.now();
        }
    }
}
