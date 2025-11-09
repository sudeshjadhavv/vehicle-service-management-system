package com.example.vehicleservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    public enum Status { BOOKED, IN_PROGRESS, COMPLETED, CANCELLED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.BOOKED;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnoreProperties({"vehicles"})
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private User assignedMechanic; // mechanic user reference, can be null

    private LocalDateTime createdAt = LocalDateTime.now();

//    public ServiceRequest() {}
//
//    public ServiceRequest(String description, Vehicle vehicle) {
//        this.description = description;
//        this.vehicle = vehicle;
//        this.status = Status.BOOKED;
//    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public User getAssignedMechanic() {
		return assignedMechanic;
	}

	public void setAssignedMechanic(User assignedMechanic) {
		this.assignedMechanic = assignedMechanic;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

   
}
