package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.model.Therapy;
import com.codecool.healnaturalisapp.service.TherapyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {
    private final TherapyService therapyService;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Value("${data.initializer.multiplyTherapies}")
    private boolean multiplyTherapies;

    public DataInitializer(TherapyService therapyService) {
        this.therapyService = therapyService;
    }

    @Override
    public void run(String... args) {

        try {
            if (therapyService.countTherapies() == 0) {
                populateTherapies();
            }
        } catch (DataAccessException e) {
            logger.error("Error occurred while counting therapies", e);
        }
    }

    private void populateTherapies() {
        // Read the JSON file and convert it into a List of Therapy objects
        List<Therapy> originalTherapies = therapyService.readTherapiesFromJson();

        //Check for null list or null elements in the list.If null is found, exit the method with a warning
        if (!therapyService.validateTherapiesList(originalTherapies)) {
            logger.warn("Therapies list is invalid, not saving to database!");
            return;
        }
        // Multiply the therapies if needed according to the value of the multiplyTherapies property
        // For testing purposes, we may want to multiply the therapies and modify some of their properties
        List<Therapy> therapiesToSave = multiplyTherapies ?
                therapyService.multiplyAndModifyTherapies(originalTherapies) : originalTherapies;

        // Depending on the value of the multiplyTherapies property, we can choose to save the original therapies
        // or the modified ones
        therapyService.saveTherapiesToDatabase(therapiesToSave);
    }
}
