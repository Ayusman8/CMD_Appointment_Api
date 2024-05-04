package com.tg.cmd.cmd_appointment_service.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a user with username, password, and roles.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The password of the user.
     */
    private String userpwd;

    /**
     * The set of roles assigned to the user.
     */
    private Set<String> roles;

}
