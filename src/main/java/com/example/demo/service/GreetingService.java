package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * Created by KAI on 5/1/18.
 */
@Component
public class GreetingService {

    public String greet(String value){
        return "Hello, "+ value;
    }
}
