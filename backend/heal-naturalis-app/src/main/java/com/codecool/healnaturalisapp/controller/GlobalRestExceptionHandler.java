package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {

        // Generate a unique error ID
        UUID errorId = UUID.randomUUID();

        // Create the error details map to send to the client
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(Constants.ERROR_TIMESTAMP_KEY, LocalDateTime.now());
        errorDetails.put(Constants.ERROR_STATUS_KEY, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.put(Constants.ERROR_CODE_KEY, "INTERNAL_ERROR");
        errorDetails.put(Constants.ERROR_MESSAGE_KEY, "An unexpected error occurred.Please try again later or contact support " +
                "with the error ID.");
        errorDetails.put("error_id", errorId);

        // Log the detailed exception with the error ID
        logger.error("""
                     Error ID: {}
                     Exception occurred while processing request from user-agent: {} with accepted languages: {}
                     Error message: {}
                     Full stack trace:""",
                errorId,
                request.getHeader("User-Agent"),
                request.getHeader("Accept-Language"),
                ex.getMessage(), ex);

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
