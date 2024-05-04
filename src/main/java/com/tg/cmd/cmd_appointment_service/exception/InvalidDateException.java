package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when encountering an invalid date.
 */
public class InvalidDateException extends RuntimeException {

    /**
     * Constructs a new InvalidDateException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
