package com.example.vehicleservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.Repositories.ServiceRequestRepository;
import com.example.vehicleservice.entities.ServiceRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
	
	@Autowired
    private  ServiceRequestRepository srRepository;

  

    @Override
    public ServiceRequest create(ServiceRequest request){ 
    	return srRepository.save(request); 
    }

    @Override
    public Optional<ServiceRequest> getById(Long id){ 
    	return srRepository.findById(id); 
    }

    @Override
    public List<ServiceRequest> getAll(){ 
    	return srRepository.findAll(); 
    }

    @Override
    public List<ServiceRequest> getByVehicleId(Long vehicleId) {
        return srRepository.findByVehicleId(vehicleId);
    }

    @Override
    public ServiceRequest update(ServiceRequest request){ 
    	return srRepository.save(request); 
    }

    @Override
    public void delete(Long id){ 
    	srRepository.deleteById(id); 
    }
}
