package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when user credentials are invalid.
 */
public class InvalidUserCredentialsException extends RuntimeException {

    /**
     * Constructs a new InvalidUserCredentialsException with the specified detail message.
     *
     * @param msg the detail message
     */
    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }
}
