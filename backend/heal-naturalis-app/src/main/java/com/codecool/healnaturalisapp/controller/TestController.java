package com.codecool.healnaturalisapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.165:3000", "http://192.168.1.139:3000"})
    @GetMapping("/testException")
    public String testException() {
        throw new RuntimeException("This is a test for handling exception!");
    }
}
