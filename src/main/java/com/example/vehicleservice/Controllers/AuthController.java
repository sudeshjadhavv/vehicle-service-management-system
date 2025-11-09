package com.example.vehicleservice.Controllers;

import com.example.vehicleservice.entities.User;
import com.example.vehicleservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = authService.register(user);
        newUser.setPassword(null); // never expose password
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String token = authService.login(email, password);
        return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
    }
}
