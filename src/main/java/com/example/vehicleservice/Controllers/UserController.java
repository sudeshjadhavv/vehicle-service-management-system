package com.example.vehicleservice.Controllers;

import com.example.vehicleservice.entities.User;
import com.example.vehicleservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
   
	@Autowired
	private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
//        // basic: avoid duplicate emails
//        if (userService.getByEmail(user.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
        User created = userService.createUser(user);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<User> all(){ 
    	return userService.getAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User user = userService.getById(id).orElseThrow(() -> new RuntimeException("User not Found with Id"+" "+id));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
