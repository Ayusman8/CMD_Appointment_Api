package com.tg.cmd.cmd_appointment_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.cmd.cmd_appointment_service.dto.ResponseWrapper;
import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.services.AppointmentService;

/**
 * Controller class for managing appointment-related endpoints.
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * Endpoint to schedule a new appointment.
     * 
     * @param appointment the appointment to be scheduled
     * @return ResponseEntity indicating success or failure
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleAppointment(@RequestBody Appointment appointment) {
        try {
            appointmentService.scheduleAppointment(appointment);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get appointment details by ID.
     * 
     * @param appointmentId the ID of the appointment
     * @return ResponseEntity containing appointment details
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get_patient_by_id/{appointmentId}")
    public ResponseEntity<ResponseWrapper> getAppointmentById(@PathVariable("appointmentId") String appointmentId) {
        Appointment appointmentResponse = this.appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(appointmentResponse));
    }

    /**
     * Endpoint to cancel an appointment by ID.
     * 
     * @param appointmentId the ID of the appointment to be canceled
     * @return ResponseEntity containing the updated appointment details
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cancel_appointment/{appointmentId}")
    public ResponseEntity<ResponseWrapper> updateAppointment(@PathVariable("appointmentId") String appointmentId) {
        Appointment appointmentResponse = appointmentService.cancelAppointment(appointmentId, false);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(appointmentResponse));
    }
}
