package com.athisii.patientservice.controller;

import com.athisii.patientservice.dto.PatientRequestDto;
import com.athisii.patientservice.dto.PatientResponseDto;
import com.athisii.patientservice.dto.ResponseDto;
import com.athisii.patientservice.dto.validators.CreatePatientValidationGroup;
import com.athisii.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/4/25
 */

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseDto<List<PatientResponseDto>> getPatients() {
        return ResponseDto.onSuccess("Data fetched successfully", patientService.getPatients());
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseDto<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseDto.onSuccess("Data created successfully", patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Patient")
    public ResponseDto<PatientResponseDto> updatePatient(@PathVariable("id") Long id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseDto.onSuccess("Data updated successfully", patientService.updatePatient(id, patientRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseDto<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return ResponseDto.onSuccess("Data deleted successfully", null);
    }
}
