package com.tg.cmd.cmd_appointment_service.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.cmd.cmd_appointment_service.exception.AppointmentAlreadyExistsException;
import com.tg.cmd.cmd_appointment_service.exception.AppointmentNotFoundException;
import com.tg.cmd.cmd_appointment_service.exception.DoctorNotAvailableException;
import com.tg.cmd.cmd_appointment_service.exception.InvalidDateException;
import com.tg.cmd.cmd_appointment_service.externalservices.DoctorServiceFactory;
import com.tg.cmd.cmd_appointment_service.externalservices.IDoctorService;
import com.tg.cmd.cmd_appointment_service.externalservices.IPatientService;
import com.tg.cmd.cmd_appointment_service.externalservices.PatientServiceFactory;
import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.models.AppointmentStatus;
import com.tg.cmd.cmd_appointment_service.repositories.AppointmentRepository;
import com.tg.cmd.cmd_appointment_service.services.AppointmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the {@link AppointmentService} interface.
 */
@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    private IDoctorService doctorService;
    private IPatientService patientService;

    public AppointmentServiceImpl() {
		// TODO Auto-generated constructor stub
    	doctorService = DoctorServiceFactory.create("mock");
    	patientService = PatientServiceFactory.create("mock");
	}
    
    /**
     * Schedules a new appointment.
     * 
     * @param appointment The appointment to be scheduled.
     * @return The scheduled appointment.
     */
    @Override
    public Appointment scheduleAppointment(Appointment appointment) {
        log.info("Scheduling Appointment...");
        
        boolean patientStatus = false;
        boolean doctorStatus = false;
        boolean isDoctorAvailableInSlot = false;
        boolean isValidDateAndTime = true;
        
        Appointment appointmentRequest = null;
        
        if(validateAppointmentDateAndTime(appointment.getAppointmentDate(), appointment.getAppointmentTime())) {
            log.info("Valid date and time");
            isValidDateAndTime = true;
        } else {
            log.info("Invalid date and time");
            throw new InvalidDateException("Invalid date and time");
        }
        
        if(checkIfDoctorIsAvailable(appointment)) {
            log.info("Doctor is available");
            patientStatus = true;
        } else {
            log.info("Doctor is not available");
            throw new DoctorNotAvailableException("Doctor is not available in clinic");
        }
        
        if(checkPatientStatus(appointment)) {
            log.info("Patient is active");
            doctorStatus = true;
        } else {
            log.info("Patient is not active");
        }
        
        if(compareDoctorSlots(appointment)) {
            log.info("Doctor is available in requested slot");
            isDoctorAvailableInSlot = true;
        } else {
            log.info("Doctor is not available at " + appointment.getAppointmentTime());
        }

        if (appointment.getAppointmentId() != null) {
            appointmentRequest = this.appointmentRepository.findById(appointment.getAppointmentId()).orElse(null);
        }

        if (appointmentRequest != null) {
            log.info("Appointment Already Exists");
            throw new AppointmentAlreadyExistsException("Appointment details Already Found!!!");
        } else {
            if (isValidDateAndTime && patientStatus && doctorStatus && isDoctorAvailableInSlot) {
                log.info("Appointment Created");
                log.info("Saving in DB...");
                appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
                return appointmentRepository.save(appointment);
            } else {
                throw new AppointmentNotFoundException("BAD DATA FOR SCHEDULING APPOINTMENT");
            }
        }
    }

    /**
     * Retrieves an appointment by its ID.
     * 
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    @Override
    public Appointment getAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    /**
     * Cancels an appointment by its ID.
     * 
     * @param appointmentId The ID of the appointment to cancel.
     * @param status        The status to set for the appointment.
     * @return The cancelled appointment.
     */
    @Override
    public Appointment cancelAppointment(String appointmentId, boolean status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        appointment.setAppointmentActiveStatus(status);
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        log.info("Appointment is cancelled");
        return appointmentRepository.save(appointment);
    }

    protected boolean validateAppointmentDateAndTime(LocalDate date, LocalTime time) {
        log.info("Validating date and time...");
        LocalDate today = LocalDate.now();
        boolean isValidDate = date.isBefore(today.plusDays(30)) && date.isAfter(today);
        return isValidDate;
    }

    protected boolean checkIfDoctorIsAvailable(Appointment appointment) {
        log.info("Checking doctor's availability...");
        return doctorService.getDoctorAvailabilityByDoctorApi(appointment);
    }

    protected boolean compareDoctorSlots(Appointment appointment) {
        log.info("Checking doctor's availability...");
        return doctorService.checkDoctorForGivenTimeSlot(appointment);
    }

    protected boolean checkPatientStatus(Appointment appointment) {
        log.info("Checking patient status...");
        return patientService.getPatientStatusFromPatientApi(appointment);
    }
}
