package com.tg.cmd.cmd_appointment_service.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Appointment entity representing an appointment in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointment {

    // Appointment ID generated using custom ID generator
    @Id
    @GenericGenerator(name = "appointmentId", strategy = "com.tg.cmd.cmd_appointment_service.models.IdGenerator")
    @GeneratedValue(generator = "appointmentId")
    @Column(name = "appointment_id")
    private String appointmentId;
    
    // Patient name
    @Column(name = "patient_name")
    private String patientName;
    
    // Patient ID
    @Column(name = "patient_id")
    private String patientId;
    
    // Doctor name
    @Column(name = "doctor_name")
    private String doctorName;
    
    // Doctor ID
    @Column(name = "doctor_id")
    private String doctorId;
    
    // Date of the appointment
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    
    // Time of the appointment
    @DateTimeFormat(iso = ISO.TIME)
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    
    // Patient email ID
    @Column(name = "patient_email_id")
    private String patientEmailId;
    
    // Patient phone number
    @Column(name = "patient_phone_number")
    private long patientPhoneNumber;
    
    // Purpose of visit enum
    @Enumerated(EnumType.STRING)
    @Column(name = "purpose_of_visit")
    private Purpose purposeOfVisit;
    
    // Appointment details
    @Column(name = "appointment_details")
    private String appointmentDetails;
    
    // Appointment active status
    @Column(name = "is_active")
    private boolean appointmentActiveStatus;
    
    // Appointment status enum
    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;
    
    // Date of creation
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    
    // Date of modification
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    // Created by
    @Column(name = "created_by")
    private String createdBy;

    // Modified by
    @Column(name = "modified_by")
    private String modifiedBy;
    
}
