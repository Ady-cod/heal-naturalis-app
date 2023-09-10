package com.codecool.healnaturalisapp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonReader {
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(JsonReader.class);

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // This is a generic method to read a JSON file and parse it into a list of objects of type T.
    // It throws a runtime exception if there is an issue reading the file, which should be caught and handled by the calling method.
    public <T> List<T> readJsonList(Resource resource, TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            logger.error("Unable to read json file: " + resource, e);
            throw new RuntimeException("Unable to read json file: " + resource, e);
        }
    }
}