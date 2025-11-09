package com.example.vehicleservice.Controllers;

import com.example.vehicleservice.entities.ServiceRequest;
import com.example.vehicleservice.entities.User;
import com.example.vehicleservice.entities.Vehicle;
import com.example.vehicleservice.service.ServiceRequestService;
import com.example.vehicleservice.service.UserService;
import com.example.vehicleservice.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {

	@Autowired
    private ServiceRequestService requestService;
	
	@Autowired
    private VehicleService vehicleService;
	
	@Autowired
    private UserService userService;

//    public ServiceRequestController(ServiceRequestService requestService, VehicleService vehicleService, UserService userService) {
//        this.requestService = requestService;
//        this.vehicleService = vehicleService;
//        this.userService = userService;
//    }

    // create request, pass vehicleId and description in body JSON
	@PostMapping
	public ResponseEntity<?> create(@RequestParam Long vehicleId, @RequestBody ServiceRequest body) {
	    Optional<Vehicle> v = vehicleService.getById(vehicleId);
	    if (!v.isPresent()) return ResponseEntity.badRequest().body("Vehicle not found");

	    // Attach vehicle to the incoming request body
	    body.setVehicle(v.get());

	    // Now save it
	    ServiceRequest created = requestService.create(body);
	    return ResponseEntity.ok(created);
	}


    @GetMapping
    public List<ServiceRequest> getAllServiceRequest(){ 
    	return requestService.getAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable Long id) {
  ServiceRequest r = requestService.getById(id).orElseThrow(() -> new RuntimeException("ServiceRequest is now found with Id"+" "+id));
        return ResponseEntity.ok(r);
    }

    // assign mechanic to request (admin action)
    @PostMapping("/{requestId}/assign")
    public ResponseEntity<?> assignMechanic(@PathVariable Long requestId, @RequestParam Long mechanicId) {
        Optional<ServiceRequest> rOpt = requestService.getById(requestId);
        Optional<User> mOpt = userService.getById(mechanicId);
        if (!rOpt.isPresent()) return ResponseEntity.badRequest().body("Request not found");
        if (!mOpt.isPresent()) return ResponseEntity.badRequest().body("Mechanic not found");
        User mechanic = mOpt.get();
        if (mechanic.getRole() != User.Role.MECHANIC) {
            return ResponseEntity.badRequest().body("User is not a mechanic");
        }
        ServiceRequest r = rOpt.get();
        r.setAssignedMechanic(mechanic);
        r.setStatus(ServiceRequest.Status.IN_PROGRESS);
        ServiceRequest updated = requestService.update(r);
        return ResponseEntity.ok(updated);
    }

    // update status (mechanic or admin)
    @PutMapping("/{requestId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long requestId, @RequestParam ServiceRequest.Status status) {
        Optional<ServiceRequest> rOpt = requestService.getById(requestId);
        if (!rOpt.isPresent()) return ResponseEntity.badRequest().body("Request not found");
        ServiceRequest r = rOpt.get();
        r.setStatus(status);
        ServiceRequest updated = requestService.update(r);
        return ResponseEntity.ok(updated);
    }

    // get requests by vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceRequest>> byVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(requestService.getByVehicleId(vehicleId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

