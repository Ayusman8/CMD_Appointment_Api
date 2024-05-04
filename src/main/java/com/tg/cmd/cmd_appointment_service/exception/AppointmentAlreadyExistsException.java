package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when attempting to create an appointment that already exists.
 */
public class AppointmentAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new AppointmentAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
