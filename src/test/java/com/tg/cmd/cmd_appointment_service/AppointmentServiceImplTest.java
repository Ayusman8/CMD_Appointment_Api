package com.tg.cmd.cmd_appointment_service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tg.cmd.cmd_appointment_service.exception.AppointmentAlreadyExistsException;
import com.tg.cmd.cmd_appointment_service.exception.AppointmentNotFoundException;
import com.tg.cmd.cmd_appointment_service.exception.DoctorNotAvailableException;
import com.tg.cmd.cmd_appointment_service.exception.InvalidDateException;
import com.tg.cmd.cmd_appointment_service.externalservices.IDoctorService;
import com.tg.cmd.cmd_appointment_service.externalservices.IPatientService;
import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.models.AppointmentStatus;
import com.tg.cmd.cmd_appointment_service.repositories.AppointmentRepository;
import com.tg.cmd.cmd_appointment_service.services.impl.AppointmentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentServiceImplTest {
    
    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;
    
    @Mock
    private AppointmentRepository appointmentRepository;
    
    @Mock
    private IDoctorService doctorService;
    
    @Mock
    private IPatientService patientService;
    
    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testScheduleAppointment_Success() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setAppointmentTime(LocalTime.NOON);
        when(doctorService.getDoctorAvailabilityByDoctorApi(appointment)).thenReturn(true);
        when(patientService.getPatientStatusFromPatientApi(appointment)).thenReturn(true);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        
        // Act
        Appointment result = appointmentServiceImpl.scheduleAppointment(appointment);
        
        // Assert
        assert result != null;
        assert result.getAppointmentStatus() == AppointmentStatus.SCHEDULED;
    }
    
    @Test
    public void testScheduleAppointment_AppointmentAlreadyExists() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("existingAppointmentId");
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setAppointmentTime(LocalTime.NOON);
        when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(java.util.Optional.of(appointment));

        // Act & Assert
        assertThrows(AppointmentAlreadyExistsException.class, () -> {
            appointmentServiceImpl.scheduleAppointment(appointment);
        });
    }
    
    @Test
    public void testScheduleAppointment_DoctorNotAvailable() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setAppointmentTime(LocalTime.NOON);
        when(doctorService.getDoctorAvailabilityByDoctorApi(appointment)).thenReturn(false);

        // Act & Assert
        assertThrows(DoctorNotAvailableException.class, () -> {
            appointmentServiceImpl.scheduleAppointment(appointment);
        });
    }

    @Test
    public void testScheduleAppointment_PatientNotActive() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setAppointmentTime(LocalTime.NOON);
        when(patientService.getPatientStatusFromPatientApi(appointment)).thenReturn(false);

        // Act & Assert
        assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentServiceImpl.scheduleAppointment(appointment);
        });
    }

    @Test
    public void testScheduleAppointment_InvalidDateTime() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.now().minusDays(1));
        appointment.setAppointmentTime(LocalTime.NOON);

        // Act & Assert
        assertThrows(InvalidDateException.class, () -> {
            appointmentServiceImpl.scheduleAppointment(appointment);
        });
    }
}
