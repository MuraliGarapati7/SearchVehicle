package com.vehicle.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The Class VehicleListDTO.
 */
public class VehicleListDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The vehicle.
     */
    private List<VehicleDTO> vehicle;

    public VehicleListDTO() {
    }

    public VehicleListDTO(List<VehicleDTO> vehicle) {
        this.vehicle = vehicle;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<VehicleDTO> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<VehicleDTO> vehicle) {
        this.vehicle = vehicle;
    }
}
