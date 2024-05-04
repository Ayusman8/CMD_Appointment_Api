package com.tg.cmd.cmd_appointment_service.models;

import java.time.LocalDate;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Custom ID generator for generating unique identifiers for entities.
 */
@SuppressWarnings("serial")
public class IdGenerator implements IdentifierGenerator {
    
    /**
     * Generate a unique identifier.
     * 
     * @param session The session implementation.
     * @param object  The object for which the identifier is being generated.
     * @return A unique identifier based on the current date and a random number.
     */
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        // Generate a unique ID based on the current date and a random number
        return "APT" + LocalDate.now().getYear() + LocalDate.now().getMonth() + new Random().nextLong(1000);
    }
}
