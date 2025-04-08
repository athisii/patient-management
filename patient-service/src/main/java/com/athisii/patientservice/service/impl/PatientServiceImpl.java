package com.athisii.patientservice.service.impl;

import com.athisii.patientservice.dto.PatientRequestDto;
import com.athisii.patientservice.dto.PatientResponseDto;
import com.athisii.patientservice.exception.EmailAlreadyExistsException;
import com.athisii.patientservice.grpc.BillingServiceGrpcClient;
import com.athisii.patientservice.kafka.KafkaProducer;
import com.athisii.patientservice.mapper.PatientMapper;
import com.athisii.patientservice.repository.PatientRepository;
import com.athisii.patientservice.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author athisii
 * @version 1.0
 * @since 3/19/25 8:10
 */


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientServiceImpl implements PatientService {
    final PatientRepository patientRepository;
    final BillingServiceGrpcClient billingServiceGrpcClient;
    final KafkaProducer kafkaProducer;

    public PatientServiceImpl(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        if (patientRepository.existsByEmail(patientRequestDto.email())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.email());
        }
        var newPatient = patientRepository.save(PatientMapper.toEntity(patientRequestDto));
        billingServiceGrpcClient.createBillingAccount(newPatient.getId(), newPatient.getName(), newPatient.getEmail()); // make GRPC call
        kafkaProducer.sendEvent(newPatient);
        return PatientMapper.toDto(newPatient);
    }

    @Override
    public List<PatientResponseDto> getPatients() {
        return patientRepository.findAll().stream().map(PatientMapper::toDto).toList();
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto patientRequestDto) {
        var patient = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDto.email(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.email());
        }
        patient.setName(patientRequestDto.name());
        patient.setEmail(patientRequestDto.email());
        patient.setPhone(patientRequestDto.phone());
        patient.setBirthDate(patientRequestDto.birthDate());
        patient.setAddress(patientRequestDto.address());
        return PatientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
