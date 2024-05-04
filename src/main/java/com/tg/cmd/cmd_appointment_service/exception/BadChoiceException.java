package com.tg.cmd.cmd_appointment_service.exception;

/**
 * Exception thrown when encountering a bad choice.
 */
public class BadChoiceException extends RuntimeException {

    /**
     * Constructs a new BadChoiceException with the specified detail message.
     *
     * @param msg the detail message
     */
    public BadChoiceException(String msg) {
        super(msg);
    }
}
