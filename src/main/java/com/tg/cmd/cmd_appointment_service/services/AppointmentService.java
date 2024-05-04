package com.tg.cmd.cmd_appointment_service.services;

import com.tg.cmd.cmd_appointment_service.models.Appointment;

/**
 * Service interface for managing appointments.
 */
public interface AppointmentService {

    /**
     * Method to schedule an appointment.
     * 
     * @param appointment The appointment to be scheduled.
     * @return The scheduled appointment.
     */
    public Appointment scheduleAppointment(Appointment appointment);
    
    /**
     * Method to retrieve an appointment by ID.
     * 
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    public Appointment getAppointmentById(String appointmentId);
    
    /**
     * Method to cancel an appointment by ID.
     * 
     * @param appointmentId The ID of the appointment to cancel.
     * @param status        The status of the appointment.
     * @return The cancelled appointment.
     */
    public Appointment cancelAppointment(String appointmentId, boolean status);
}
