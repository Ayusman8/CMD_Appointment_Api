package com.tg.cmd.cmd_appointment_service.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when encountering a malformed JWT token during authentication.
 */
public class JwtTokenMalformedException extends AuthenticationException {

    /**
     * Constructs a new JwtTokenMalformedException with the specified detail message.
     *
     * @param msg the detail message
     */
    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
