package com.example.vehicleservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.Repositories.UserRepository;
import com.example.vehicleservice.entities.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user){ 
    	return userRepository.save(user); 
    }

    @Override
    public Optional<User> getById(Long id){ 
    	return userRepository.findById(id); 
    }

    @Override
    public List<User> getAll(){ 
    	return userRepository.findAll(); 
    }

    @Override
    public Optional<User> getByEmail(String email){ 
    	return userRepository.findByEmail(email); 
    }

    @Override
    public void deleteById(Long id){ 
    	userRepository.deleteById(id); 
    }
}

