package com.tg.cmd.cmd_appointment_service.externalservices;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.models.Days;
import com.tg.cmd.cmd_appointment_service.models.Doctor;

/**
 * Mock implementation of IDoctorService for testing purposes.
 */
public class DoctorServiceMockImpl implements IDoctorService {

    // Instance of Doctor class for mock implementation
    private Doctor doctor = new Doctor();

    /**
     * Mock method to get doctor availability.
     *
     * @param appointment The appointment object containing doctor information.
     * @return The availability status of the doctor.
     */
    @Override
    public boolean getDoctorAvailabilityByDoctorApi(Appointment appointment) {

        // Mock implementation to set doctor availability status
        doctor.setStatus(true);

        // Return the doctor availability status
        return doctor.isStatus();
    }

    /**
     * Mock method to check doctor availability for a given time slot.
     *
     * @param appointment The appointment object containing doctor information.
     * @return True if the doctor is available for the given time slot, otherwise false.
     */
    @Override
    public boolean checkDoctorForGivenTimeSlot(Appointment appointment) {

        boolean isTimeSlotAvailable = false;

        // Mock doctor's weekly active hours
        List<String> slots = new ArrayList<>();
        slots.add("10,13");
        slots.add("15,22");
        Map<Days, List<String>> map = new HashMap<>();
        map.put(Days.MONDAY, slots);
        map.put(Days.TUESDAY, slots);
        map.put(Days.WEDNESDAY, slots);
        map.put(Days.THURSDAY, slots);
        map.put(Days.FRIDAY, slots);
        map.put(Days.SATURDAY, slots);

        // Set doctor's weekly active hours
        doctor.setWeeklyActiveHours(map);

        // Get the day of the appointment
        String day = appointment.getAppointmentDate().getDayOfWeek().toString();
        
        // Iterate through the days in the weekly active hours
        Set<Days> keySet = map.keySet();
        for (Days days : keySet) {
            // Check if the appointment day matches a day in the active hours
            if (days.toString().equalsIgnoreCase(day)) {
                // Iterate through the time slots for the appointment day
                for (String slot : map.get(days)) {
                    // Split the time slot into start and end times
                    String[] doctorAvailableTime = slot.split(",");
                    // Check if the appointment time falls within the time slot
                    if (!appointment.getAppointmentTime().isBefore(LocalTime.of(Integer.parseInt(doctorAvailableTime[0]), 0))
                            && !appointment.getAppointmentTime().isAfter(LocalTime.of(Integer.parseInt(doctorAvailableTime[1]), 0))) {
                        isTimeSlotAvailable = true;
                        break;
                    }
                }
            }
        }
        return isTimeSlotAvailable;
    }

}
