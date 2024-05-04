package com.tg.cmd.cmd_appointment_service.externalservices;

import com.tg.cmd.cmd_appointment_service.models.Appointment;

/**
 * Interface for Doctor Service.
 */
public interface IDoctorService {

    /**
     * Method to get doctor availability by calling the doctor API.
     *
     * @param appointment The appointment object containing doctor information.
     * @return True if the doctor is available, otherwise false.
     */
    boolean getDoctorAvailabilityByDoctorApi(Appointment appointment);
        
    /**
     * Method to check doctor availability for a given time slot.
     *
     * @param appointment The appointment object containing doctor information.
     * @return True if the doctor is available for the given time slot, otherwise false.
     */
    boolean checkDoctorForGivenTimeSlot(Appointment appointment);
}
