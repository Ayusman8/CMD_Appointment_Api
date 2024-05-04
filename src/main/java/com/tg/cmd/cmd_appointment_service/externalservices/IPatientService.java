package com.tg.cmd.cmd_appointment_service.externalservices;

import com.tg.cmd.cmd_appointment_service.models.Appointment;

/**
 * Interface for Patient Service.
 */
public interface IPatientService {

    /**
     * Method to get patient status from patient API.
     *
     * @param appointment The appointment object containing patient information.
     * @return True if the patient is active, otherwise false.
     */
    boolean getPatientStatusFromPatientApi(Appointment appointment); 
}
