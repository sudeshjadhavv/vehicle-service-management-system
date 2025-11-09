package com.example.vehicleservice.service;


import java.util.List;
import java.util.Optional;

import com.example.vehicleservice.entities.ServiceRequest;

public interface ServiceRequestService {
	
    ServiceRequest create(ServiceRequest request);
    
    Optional<ServiceRequest> getById(Long id);
    
    List<ServiceRequest> getAll();
    
    List<ServiceRequest> getByVehicleId(Long vehicleId);
    
    ServiceRequest update(ServiceRequest request);
    
    void delete(Long id);
}
