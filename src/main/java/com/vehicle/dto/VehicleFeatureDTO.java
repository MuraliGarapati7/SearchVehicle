package com.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class VehicleFeatureDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("Exterior")
    private List<String> exterior;
    @JsonProperty("Interior")
    private List<String> interior;

    public VehicleFeatureDTO() {

    }

    public VehicleFeatureDTO(List<String> exterior, List<String> interior) {
        this.exterior = exterior;
        this.interior = interior;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getExterior() {
        return exterior;
    }

    public void setExterior(List<String> exterior) {
        this.exterior = exterior;
    }

    public List<String> getInterior() {
        return interior;
    }

    public void setInterior(List<String> interior) {
        this.interior = interior;
    }
}
