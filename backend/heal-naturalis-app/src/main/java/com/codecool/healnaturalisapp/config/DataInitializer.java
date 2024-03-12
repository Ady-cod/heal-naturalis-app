package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.service.TherapyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {
    private final TherapyService therapyService;

    public DataInitializer(TherapyService therapyService) {
        this.therapyService = therapyService;
    }

    @Override
    public void run(String... args) {
            if (therapyService.countTherapies() == 0) {
                therapyService.populateTherapies();
            }
    }


}
