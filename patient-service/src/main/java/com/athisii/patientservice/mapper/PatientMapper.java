package com.athisii.patientservice.mapper;

import com.athisii.patientservice.dto.PatientRequestDto;
import com.athisii.patientservice.dto.PatientResponseDto;
import com.athisii.patientservice.entity.Patient;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/4/25
 */

public class PatientMapper {
    private PatientMapper() {
    }

    public static PatientResponseDto toDto(Patient patient) {
        return new PatientResponseDto(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getBirthDate(), patient.getAddress());
    }

    public static Patient toEntity(PatientRequestDto patientRequestDto) {
        var patient = new Patient();
        patient.setName(patientRequestDto.name());
        patient.setEmail(patientRequestDto.email());
        patient.setPhone(patientRequestDto.phone());
        patient.setBirthDate(patientRequestDto.birthDate());
        patient.setRegisteredDate(patientRequestDto.registeredDate());
        patient.setAddress(patientRequestDto.address());
        return patient;
    }
}
