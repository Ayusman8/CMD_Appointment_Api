package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when attempting to create a doctor that already exists.
 */
public class DoctorAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new DoctorAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public DoctorAlreadyExistsException(String message) {
        super(message);
    }
}
