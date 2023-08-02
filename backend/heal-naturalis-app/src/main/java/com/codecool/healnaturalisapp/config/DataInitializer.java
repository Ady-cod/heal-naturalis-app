package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.model.Therapy;
import com.codecool.healnaturalisapp.repository.TherapyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {
    private final TherapyRepository therapyRepository;
    private final ObjectMapper objectMapper;

    @Value("classpath:data/therapies.json")
    private Resource therapiesResource;


    public DataInitializer(TherapyRepository therapyRepository, ObjectMapper objectMapper) {
        this.therapyRepository = therapyRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if (therapyRepository.count() == 0) {

            try {
                for (int i = 0; i < 50; i++) { // Multiply by 50 to populate database for testing purposes

                    // Read the JSON file and convert it into a List of Therapy objects
                    List<Therapy> therapies = readJsonList(therapiesResource, new TypeReference<>() {
                    });

                    // Set unique values for some fields and save all therapies to the database
                    for (Therapy therapy : therapies) {
                        therapy.setName(therapy.getName() + " " + (i+1));
                        therapy.setDescription(therapy.getDescription().repeat(3) );
                        therapy.setPrice(therapy.getPrice().add(new BigDecimal(i)));
                        therapy.setTherapistName(therapy.getTherapistName() + " " + (i+1));
                        therapyRepository.save(therapy);
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to save therapies: " + e.getMessage());
            }
        }
    }

    private <T> List<T> readJsonList(Resource resource, TypeReference<List<T>> typeReference) {
        try(InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read json file: " + resource, e);
        }
    }

}
