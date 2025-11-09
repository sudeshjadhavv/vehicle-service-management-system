package com.example.vehicleservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehicleservice.entities.ServiceRequest;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
	
    List<ServiceRequest> findByVehicleId(Long vehicleId);
}
