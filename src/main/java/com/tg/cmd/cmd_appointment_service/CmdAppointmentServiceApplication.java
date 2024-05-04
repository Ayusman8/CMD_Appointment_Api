package com.tg.cmd.cmd_appointment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CmdAppointmentServiceApplication {

    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(CmdAppointmentServiceApplication.class, args);
    }
    
    /**
     * Bean definition for RestTemplate, used for making HTTP requests.
     * 
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
