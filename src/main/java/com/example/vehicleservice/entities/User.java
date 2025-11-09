package com.example.vehicleservice.entities;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class User {
    public enum Role { ADMIN, CUSTOMER, MECHANIC }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password; // plain text for now (no security) — change later!

    @Enumerated(EnumType.STRING)
    private Role role;

    // One user (customer) may have multiple vehicles
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Vehicle> vehicles = new ArrayList<>();

//    public User() {}
//
//    public User(String name, String email, String password, Role role) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

 // getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

    
   
}

