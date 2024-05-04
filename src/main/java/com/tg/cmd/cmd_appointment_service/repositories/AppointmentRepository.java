package com.tg.cmd.cmd_appointment_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.cmd.cmd_appointment_service.models.Appointment;

/**
 * Spring Data JPA repository interface for the Appointment entity.
 * This interface inherits methods for CRUD operations from JpaRepository.
 * No additional methods are defined here because JpaRepository provides all necessary CRUD operations.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
