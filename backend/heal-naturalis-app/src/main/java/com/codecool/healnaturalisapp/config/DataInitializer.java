package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.service.TherapyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TherapyService therapyService;

    @Override
    public void run(String... args) {
            if (therapyService.countTherapies() == 0) {
                therapyService.populateTherapies();
            }
    }


}
