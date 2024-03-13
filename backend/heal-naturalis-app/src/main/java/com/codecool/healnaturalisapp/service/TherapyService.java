package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.model.Therapy;
import com.codecool.healnaturalisapp.repository.TherapyRepository;
import com.codecool.healnaturalisapp.util.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapyService {

    private final TherapyRepository therapyRepository;

    private final JsonReader jsonReader;
    private static final Logger logger = LoggerFactory.getLogger(TherapyService.class);

    @Value("classpath:data/therapies.json")
    private Resource therapiesResource;

    @Value("${therapy.initializer.isMultiplyingTherapies}")
    private boolean isMultiplyingTherapies;

    @Value("${application.multiplier}")
    private int multiplier;

    @Value("${therapy.initializer.descriptionMultiplier}")
    private int descriptionMultiplier;


    public long countTherapies() {
        try{
            return therapyRepository.count();
        } catch (DataAccessException e) {
            logger.error("Error occurred while counting therapies", e);
            throw e;
        }
    }

    private List<Therapy> readTherapiesFromJson() {
        List<Therapy> originalTherapies;
        try {
            // Read the JSON file and convert it into a List of Therapy objects
            originalTherapies = jsonReader.readJsonList(therapiesResource, new TypeReference<>() {
            });
        } catch (RuntimeException e) {
            logger.error("Error occurred while reading therapies", e);
            return new ArrayList<>();
        }
        return originalTherapies;
    }

    private boolean validateTherapiesList(List<Therapy> therapies) {
        // Check if the therapies list is null or contains null values and log a warning if so
        if (therapies == null || therapies.contains(null)) {
            logger.warn("Therapies list is null or contains null values!");
            return false;
        }
        return true;
    }

    private List<Therapy> multiplyAndModifyTherapies(List<Therapy> originalTherapies) {
        List<Therapy> therapies = new ArrayList<>();
        try {
            for (int i = 0; i < multiplier; i++) { // Multiply with the multiplier value to populate database for testing purposes

                // Create a deep copy of the original Therapy objects
                // And set unique values for some fields and save all new Therapy objects to a new list
                for (Therapy originalTherapy : originalTherapies) {
                    Therapy therapy = new Therapy(originalTherapy);
                    therapy.setName(therapy.getName() + " " + (i + 1));
                    therapy.setDescription(therapy.getDescription().repeat(descriptionMultiplier));
                    therapy.setPrice(therapy.getPrice().add(new BigDecimal(i)));
                    therapy.setTherapistName(therapy.getTherapistName() + " " + (i + 1));
                    therapies.add(therapy);
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException | ArithmeticException e) {
            logger.error("Error occurred while modifying and multiplying therapies", e);
        }
        return therapies;
    }

    private void saveTherapiesToDatabase(List<Therapy> therapies) {
        try {
            therapyRepository.saveAll(therapies);
        } catch (DataAccessException e) {
            logger.error("Error occurred while saving therapies", e);
        }
    }

    public void populateTherapies() {
        // Read the JSON file and convert it into a List of Therapy objects
        List<Therapy> originalTherapies = readTherapiesFromJson();

        //Check for null list or null elements in the list.If null is found, exit the method with a warning
        if (! validateTherapiesList(originalTherapies)) {
            logger.warn("Therapies list is invalid, not saving to database!");
            return;
        }
        // Multiply the therapies if needed according to the value of the multiplyTherapies property
        // For testing purposes, we may want to multiply the therapies and modify some of their properties
        List<Therapy> therapiesToSave = isMultiplyingTherapies ?
                multiplyAndModifyTherapies(originalTherapies) : originalTherapies;

        // Depending on the value of the multiplyTherapies property, we can choose to save the original therapies
        // or the modified ones
        saveTherapiesToDatabase(therapiesToSave);
    }
}
