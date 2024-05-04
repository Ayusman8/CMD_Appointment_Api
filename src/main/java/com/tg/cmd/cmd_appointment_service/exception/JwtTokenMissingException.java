package com.tg.cmd.cmd_appointment_service.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when a JWT token is missing during authentication.
 */
public class JwtTokenMissingException extends AuthenticationException {

    /**
     * Constructs a new JwtTokenMissingException with the specified detail message.
     *
     * @param msg the detail message
     */
    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
