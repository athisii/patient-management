package com.athisii.patientservice.service;


import com.athisii.patientservice.dto.PatientRequestDto;
import com.athisii.patientservice.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);

    List<PatientResponseDto> getPatients();

    PatientResponseDto updatePatient(Long id, PatientRequestDto patientRequestDto);
    void deletePatient(Long id);
}
