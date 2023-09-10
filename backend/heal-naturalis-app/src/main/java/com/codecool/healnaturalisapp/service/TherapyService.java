package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.model.Therapy;
import com.codecool.healnaturalisapp.repository.TherapyRepository;
import com.codecool.healnaturalisapp.util.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class TherapyService {
    private final TherapyRepository therapyRepository;
    private final JsonReader jsonReader;
    private static final Logger logger = LoggerFactory.getLogger(TherapyService.class);

    @Value("classpath:data/therapies.json")
    private Resource therapiesResource;

    @Value("${data.initializer.multiplier}")
    private int multiplier;

    @Value("${data.initializer.multiplyTherapyDescription}")
    private int multiplyTherapyDescription;

    public TherapyService(TherapyRepository therapyRepository, JsonReader jsonReader) {
        this.therapyRepository = therapyRepository;
        this.jsonReader = jsonReader;
    }

    public long countTherapies() {
        try{
            return therapyRepository.count();
        } catch (DataAccessException e) {
            logger.error("Error occurred while counting therapies", e);
            throw e;
        }
    }

    public List<Therapy> readTherapiesFromJson() {
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

    public boolean validateTherapiesList(List<Therapy> therapies) {
        // Check if the therapies list is null or contains null values and log a warning if so
        if (therapies == null || therapies.contains(null)) {
            logger.warn("Therapies list is null or contains null values!");
            return false;
        }
        return true;
    }

    public List<Therapy> multiplyAndModifyTherapies(List<Therapy> originalTherapies) {
        List<Therapy> therapies = new ArrayList<>();
        try {
            for (int i = 0; i < multiplier; i++) { // Multiply with the multiplier value to populate database for testing purposes

                // Create a deep copy of the original Therapy objects
                // And set unique values for some fields and save all new Therapy objects to a new list
                for (Therapy originalTherapy : originalTherapies) {
                    Therapy therapy = new Therapy(originalTherapy);
                    therapy.setName(therapy.getName() + " " + (i + 1));
                    therapy.setDescription(therapy.getDescription().repeat(multiplyTherapyDescription));
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

    public void saveTherapiesToDatabase(List<Therapy> therapies) {
        try {
            therapyRepository.saveAll(therapies);
        } catch (DataAccessException e) {
            logger.error("Error occurred while saving therapies", e);
        }
    }
}
