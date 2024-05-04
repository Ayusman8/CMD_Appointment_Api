package com.tg.cmd.cmd_appointment_service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tg.cmd.cmd_appointment_service.config.JwtUtil;
import com.tg.cmd.cmd_appointment_service.dto.JwtRequest;
import com.tg.cmd.cmd_appointment_service.dto.JwtResponse;
import com.tg.cmd.cmd_appointment_service.exception.DisabledUserException;
import com.tg.cmd.cmd_appointment_service.exception.InvalidUserCredentialsException;
import com.tg.cmd.cmd_appointment_service.models.Role;
import com.tg.cmd.cmd_appointment_service.models.User;
import com.tg.cmd.cmd_appointment_service.services.UserAuthService;
import com.tg.cmd.cmd_appointment_service.services.UserService;

/**
 * Controller class for handling JWT authentication and user registration.
 */
@RestController
public class JwtRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Endpoint for generating JWT token upon successful authentication.
     * 
     * @param jwtRequest the request object containing username and password
     * @return ResponseEntity containing JWT token
     */
    @PostMapping(value = "/signin", produces = { "application/json" })
    public ResponseEntity<JwtResponse> generateJwtToken(@RequestBody JwtRequest jwtRequest) {
        try {
            // Attempt to authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getUserPwd()));

        } catch (DisabledException e) {
            // Handle disabled user
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            // Handle invalid credentials
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        
        // Load user details after successful authentication
        UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getUserName());
        String username = userDetails.getUsername();
        String userpwd = userDetails.getPassword();
        
        // Extract user roles and convert them to a list of strings
        List<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toList());

        // Create a User object to generate JWT token
        User user = new User();
        user.setUserName(username);
        user.setPassword(userpwd);
        
        // Create a list of Role objects
        List<Role> roleList = new ArrayList<>();
        for (String roleName : roles) {
            Role role = new Role();
            role.setRoleName(roleName);
            roleList.add(role);
        }
        user.setRoles(roleList);
        
        // Generate JWT token using JwtUtil
        String token = jwtUtil.generateToken(user);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    /**
     * Endpoint for user registration.
     * 
     * @param user the user object containing registration details
     * @return ResponseEntity containing registration status
     */
    @PostMapping(value = "/signup", produces = { "application/json" })
    public ResponseEntity<String> signup(@RequestBody User user) {
        // Check if user already exists
        User userObj = userAuthService.getUserByUsername(user.getUserName());

        if (userObj == null) {
            // Save user if not already registered
            userService.saveUser(user);
            return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
        } else {
            // Return conflict if user already exists
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }
}