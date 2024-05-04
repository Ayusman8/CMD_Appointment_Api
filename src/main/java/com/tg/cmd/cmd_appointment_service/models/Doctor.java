package com.tg.cmd.cmd_appointment_service.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Doctor entity representing a doctor in the system
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    // Doctor ID
    private String doctorId;
    
    // Doctor name
    private String doctorName;
    
    // Date of birth of the doctor
    private LocalDate dateOfBirth;
    
    // Gender of the doctor
    private String gender;
    
    // Status of the doctor
    private boolean status;
    
    // Clinic name
    private String clinicName;
    
    // Map representing doctor's weekly active hours
    private Map<Days, List<String>> weeklyActiveHours;
}
