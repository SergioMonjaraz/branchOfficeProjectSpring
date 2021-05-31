package com.example.demo.initializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiStatusController {

    @Value("${spring.profiles.active}")
    private String environment;

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> show() {

        return new ResponseEntity<>(String.format("Welcome to the API in %s environment", environment), HttpStatus.OK);
    }
}