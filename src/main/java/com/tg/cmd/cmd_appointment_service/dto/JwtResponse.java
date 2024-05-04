package com.tg.cmd.cmd_appointment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a JWT authentication response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    /**
     * The JWT token generated for authentication.
     */
    private String token;

    /**
     * Get the JWT token.
     * 
     * @return the JWT token
     */
    public String getToken() {
        return token;
    }

}
