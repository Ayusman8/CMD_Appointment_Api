package com.tg.cmd.cmd_appointment_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tg.cmd.cmd_appointment_service.models.Role;
import com.tg.cmd.cmd_appointment_service.models.User;
import com.tg.cmd.cmd_appointment_service.repositories.UserRepository;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Save a user.
     * 
     * @param user The user object to save.
     * @return The saved user object.
     */
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    /**
     * Get all users.
     * 
     * @return A list of all users.
     */
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Get a user by username.
     * 
     * @param userName The username of the user.
     * @return The user object if found, otherwise null.
     */
    public User getUserByName(String userName) {
        return this.userRepository.findById(userName).orElse(null);
    }

    /**
     * Get roles associated with a user.
     * 
     * @param userName The username of the user.
     * @return A list of roles associated with the user.
     */
    public List<Role> getRoles(String userName) {
        User user = this.userRepository.findById(userName).orElse(null);
        if (user != null) {
            return user.getRoles();
        } else {
            return null;
        }
    }
}
