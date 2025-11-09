package com.example.vehicleservice.Controllers;

import com.example.vehicleservice.entities.User;
import com.example.vehicleservice.entities.Vehicle;
import com.example.vehicleservice.service.UserService;
import com.example.vehicleservice.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	
	@Autowired
    private VehicleService vehicleService;
	
	@Autowired
    private UserService userService;

//    public VehicleController(VehicleService vehicleService, UserService userService) {
//        this.vehicleService = vehicleService;
//        this.userService = userService;
//    }

    // create vehicle for a customer (pass ownerId in query or body)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vehicle vehicle, @RequestParam Long ownerId) {
        Optional<User> owner = userService.getById(ownerId);
        if (!owner.isPresent()) {
            return ResponseEntity.badRequest().body("owner not found");
        }
        vehicle.setOwner(owner.get());
        Vehicle created = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(){ 
    	return vehicleService.getAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getById(id).orElseThrow(() -> new RuntimeException("Vehicle not Found With Id"+" "+id));
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
