package com.athisii.patientservice.exception;

/**
 * @author athisii
 * @version 1.0
 * @since 3/19/25 8:14
 */

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
