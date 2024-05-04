package com.tg.cmd.cmd_appointment_service.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuration class for customizing repository REST configuration.
 */
public class AppDaoConfig implements RepositoryRestConfigurer {

    /**
     * Method to configure repository REST configuration.
     * 
     * @param config the repository REST configuration
     * @param cors   the CORS registry
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Set whether repository methods should be exposed by default
        config.setExposeRepositoryMethodsByDefault(false);
    }
}
