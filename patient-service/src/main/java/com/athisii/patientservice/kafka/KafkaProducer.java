package com.athisii.patientservice.kafka;

import com.athisii.patientservice.entity.Patient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/5/25
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class KafkaProducer {
    final KafkaTemplate<Long, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<Long, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();
        try {
            kafkaTemplate.send("patient", patient.getId(), event.toByteArray());
        } catch (Exception ex) {
            log.error("Error sending PatientCreated event: {}", event);
        }
    }
}
