package com.example.vehicleservice.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.Repositories.VehicleRepository;
import com.example.vehicleservice.entities.Vehicle;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
   
	@Autowired
	private VehicleRepository vehicleRepository;
	

    @Override
    public Vehicle createVehicle(Vehicle vehicle){ 
    	return vehicleRepository.save(vehicle); 
    }

    @Override
    public Optional<Vehicle> getById(Long id){ 
    	return vehicleRepository.findById(id); 
    }

    @Override
    public List<Vehicle> getAll(){ 
    	return vehicleRepository.findAll(); 
    }

    @Override
    public Optional<Vehicle> getByVehicleNumber(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber);
    }

    @Override
    public void deleteById(Long id){ 
    	vehicleRepository.deleteById(id); 
    }
}
