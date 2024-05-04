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
 * Implementation of IPatientService to interact with the patient API.
 */
@Slf4j
public class PatientServiceImpl implements IPatientService{

    // Autowired AppointmentRepository for accessing appointment data
    @Autowired
    AppointmentRepository appointmentRepository;
    
    // Autowired RestTemplate for making HTTP requests
    @Autowired
    private RestTemplate restTemplate;
    
    // ResponseEntity to hold patient API response
    private ResponseEntity<String> patientResponse;
    
    // Value annotation for injecting patient API URL from properties
    @Value("${patientApiUrl}")
    private String patientApiUrl;
    
    /**
     * Method to get patient status from the patient API.
     *
     * @param appointment The appointment object containing patient information.
     * @return The status of the patient retrieved from the patient API.
     */
    @Override
    public boolean getPatientStatusFromPatientApi(Appointment appointment) {

        boolean status = false;
        
        // Call patient API to get patient status
        patientResponse = restTemplate.exchange(patientApiUrl + appointment.getPatientId(), HttpMethod.GET, null, String.class);
        
        // Log patient API response
        log.info("Response" + patientResponse.getBody());
        
        // Parse patient API response and extract patient status
        if (patientResponse.getBody() != null) {
            JsonParser springParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = springParser.parseMap(patientResponse.getBody());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("isActive".equals(entry.getKey())) {
                    status = Boolean.parseBoolean(entry.getValue().toString());
                    break;
                }//instead of traversing go with the map.get
            }
        }
        return status;
    }
}
