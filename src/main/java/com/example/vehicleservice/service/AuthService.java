package com.example.vehicleservice.service;

import com.example.vehicleservice.entities.User;

public interface AuthService {
	
    String login(String email, String password);
    
    User register(User user);
}
