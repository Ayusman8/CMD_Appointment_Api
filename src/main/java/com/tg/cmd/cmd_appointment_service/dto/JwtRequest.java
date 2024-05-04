package com.tg.cmd.cmd_appointment_service.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a JWT authentication request.
 */
@Data
public class JwtRequest {

    /**
     * The username provided in the authentication request.
     */
    private String userName;

    /**
     * The password provided in the authentication request.
     */
    private String userPwd;

}
