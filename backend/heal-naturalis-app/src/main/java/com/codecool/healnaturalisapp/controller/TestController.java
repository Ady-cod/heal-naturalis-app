package com.codecool.healnaturalisapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/testException")
    public String testException() {
        throw new RuntimeException("This is a test for handling exception!");
    }
}
