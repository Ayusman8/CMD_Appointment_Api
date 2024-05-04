package com.tg.cmd.cmd_appointment_service.externalservices;

import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.models.Patient;

/**
 * A mock implementation of the {@code IPatientService} interface.
 */
public class PatientServiceMockImpl implements IPatientService {

    /** Instance of Patient class for mock implementation */
    private Patient patient = new Patient();

    /**
     * Mock method to get patient status from patient API.
     *
     * @param appointment The appointment for which the patient status is being retrieved.
     * @return {@code true} if the patient status is active, {@code false} otherwise.
     */
    @Override
    public boolean getPatientStatusFromPatientApi(Appointment appointment) {
        // Mock implementation to set patient status
        patient.setStatus(true);
        // Return the patient status
        return patient.isStatus();
    }
}
