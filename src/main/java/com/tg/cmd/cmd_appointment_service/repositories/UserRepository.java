package com.tg.cmd.cmd_appointment_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.cmd.cmd_appointment_service.models.User;

/**
 * Spring Data JPA repository interface for the User entity.
 * This interface inherits methods for CRUD operations from JpaRepository.
 * No additional methods are defined here because JpaRepository provides all necessary CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,String>{
}
