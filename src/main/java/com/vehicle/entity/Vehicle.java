package com.vehicle.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer vehicleId;
    @OneToOne(cascade = {CascadeType.ALL})
    private VehicleDetails vehicleDetails;

    public Vehicle() {
    }

    public Vehicle(Integer vehicleId, VehicleDetails vehicleDetails) {
        this.vehicleId = vehicleId;
        this.vehicleDetails = vehicleDetails;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}
