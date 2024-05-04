package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when attempting to access an appointment that does not exist.
 */
public class AppointmentNotFoundException extends RuntimeException {

    /**
     * Constructs a new AppointmentNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
