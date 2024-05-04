package com.tg.cmd.cmd_appointment_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

/**
 * Role entity representing a role in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role")
public class Role {

    /**
     * Role ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private long roleId;

    /**
     * Role name.
     */
    @Column(name = "Role_Name", length = 50)
    private String roleName;

    /**
     * List of users associated with this role.
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /**
     * Constructor with role name parameter.
     * @param role The name of the role.
     */
    public Role(String role) {
        this.roleName = role;
    }
}
