package com.tg.cmd.cmd_appointment_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient entity representing a patient in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    /**
     * Status of the patient.
     */
    private boolean status;
}
