package com.hotel.hotel_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HealthController {
    
    @GetMapping("/api/health")
    public String checkHealth() {
        return "Hotel Service is running!";
    }
}
