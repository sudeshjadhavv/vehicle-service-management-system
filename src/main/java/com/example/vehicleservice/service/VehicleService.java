package com.example.vehicleservice.service;

import java.util.List;
import java.util.Optional;

import com.example.vehicleservice.entities.Vehicle;

public interface VehicleService {
	
    Vehicle createVehicle(Vehicle vehicle);
    
    Optional<Vehicle> getById(Long id);
    
    List<Vehicle> getAll();
    
    Optional<Vehicle> getByVehicleNumber(String vehicleNumber);
    
    void deleteById(Long id);
}

