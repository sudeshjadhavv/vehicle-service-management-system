package com.example.vehicleservice.service;

import java.util.List;
import java.util.Optional;

import com.example.vehicleservice.entities.User;

public interface UserService {
	
    User createUser(User user);
    
    Optional<User> getById(Long id);
    
    List<User> getAll();
    
    Optional<User> getByEmail(String email);
    
    void deleteById(Long id);
}
