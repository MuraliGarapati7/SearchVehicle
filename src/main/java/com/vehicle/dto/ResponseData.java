package com.vehicle.dto;

import java.io.Serializable;

public class ResponseData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private Integer statusCode;
	private String message;
	private VehicleListDTO vehicles;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public VehicleListDTO getVehicles() {
		return vehicles;
	}
	public void setVehicles(VehicleListDTO vehicles) {
		this.vehicles = vehicles;
	}
	
}
