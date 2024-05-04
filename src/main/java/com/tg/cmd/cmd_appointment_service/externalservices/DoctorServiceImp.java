package com.tg.cmd.cmd_appointment_service.externalservices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.repositories.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of IDoctorService to interact with the doctor API.
 */
@Slf4j
public class DoctorServiceImp implements IDoctorService {

    @Autowired
    AppointmentRepository appointmentRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity<String> doctorResponse;
    
    @Value("${doctorApiUrl}")
    private String doctorApiUrl;
    
    /**
     * Method to check doctor availability by calling the doctor API.
     *
     * @param appointment The appointment object containing doctor information.
     * @return The availability status of the doctor retrieved from the doctor API.
     */
    @Override
    public boolean getDoctorAvailabilityByDoctorApi(Appointment appointment) {
        
        boolean status = false;
        
        // Call the doctor API to get doctor availability
        doctorResponse = restTemplate.exchange(doctorApiUrl + appointment.getDoctorId(), HttpMethod.GET, null,
                String.class);

        // Log the response from the doctor API
        log.info("Response" + doctorResponse.getBody());

        // Parse the response and extract doctor availability status
        if (doctorResponse.getBody() != null) {
            JsonParser springParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = springParser.parseMap(doctorResponse.getBody());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("isActive".equals(entry.getKey())) {
                    status = Boolean.parseBoolean(entry.getValue().toString());
                    break;
                }
            }
        }
        return status;
    }

    /**
     * Method to check doctor availability for a given time slot.
     *
     * @param appointment The appointment object containing doctor information.
     * @return The availability status of the doctor for the given time slot.
     */
    @Override
    public boolean checkDoctorForGivenTimeSlot(Appointment appointment) {
        // TODO: Implement logic to check doctor availability for a given time slot
        return false;
    }

}
