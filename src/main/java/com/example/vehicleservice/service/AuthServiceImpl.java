package com.example.vehicleservice.service;




import com.example.vehicleservice.Repositories.UserRepository;
import com.example.vehicleservice.entities.User;
import com.example.vehicleservice.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtTokenProvider tokenProvider;

    

    @Override
    public String login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return tokenProvider.generateToken(email);
    }

    @Override
    public User register(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) throw new RuntimeException("Email already registered");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
