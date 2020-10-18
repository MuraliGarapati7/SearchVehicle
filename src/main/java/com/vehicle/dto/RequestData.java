package com.vehicle.dto;

import java.io.Serializable;

/**
 * The Class RequestData.
 */
public class RequestData implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The vehicles. */
	VehicleListDTO vehicles;

	public RequestData() {
	}

	public RequestData(VehicleListDTO vehicles) {
		this.vehicles = vehicles;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public VehicleListDTO getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehicleListDTO vehicles) {
		this.vehicles = vehicles;
	}
}
