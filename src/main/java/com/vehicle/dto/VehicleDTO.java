package com.vehicle.dto;

import java.io.Serializable;

import com.vehicle.entity.Vehicle;

public class VehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer vehicleId;
	private VehicleDetailsDTO vehicleDetails;

	public VehicleDTO(Integer vehicleId, VehicleDetailsDTO vehicleDetails) {
		this.vehicleId = vehicleId;
		this.vehicleDetails = vehicleDetails;
	}



	public VehicleDTO() {
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleDetailsDTO getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(VehicleDetailsDTO vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
}
