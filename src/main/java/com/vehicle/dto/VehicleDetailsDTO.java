package com.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class VehicleDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String make;
    private String model;
    private String modelYear;
    private String bodyStyle;
    private String engine;
    private String drivetype;
    private String color;
    @JsonProperty("MPG")
    private String mpg;
    private VehicleFeatureDTO vehicleFeature;
    private ArrayList<VehiclePriceDTO> vehiclePrice;

    public VehicleDetailsDTO() {

    }

    public VehicleDetailsDTO(String make, String model, String modelYear, String bodyStyle, String engine, String drivetype, String color, String mpg, VehicleFeatureDTO vehicleFeature, ArrayList<VehiclePriceDTO> vehiclePrice) {
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.bodyStyle = bodyStyle;
        this.engine = engine;
        this.drivetype = drivetype;
        this.color = color;
        this.mpg = mpg;
        this.vehicleFeature = vehicleFeature;
        this.vehiclePrice = vehiclePrice;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDrivetype() {
        return drivetype;
    }

    public void setDrivetype(String drivetype) {
        this.drivetype = drivetype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {
        this.mpg = mpg;
    }

    public VehicleFeatureDTO getVehicleFeature() {
        return vehicleFeature;
    }

    public void setVehicleFeature(VehicleFeatureDTO vehicleFeature) {
        this.vehicleFeature = vehicleFeature;
    }

    public ArrayList<VehiclePriceDTO> getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(ArrayList<VehiclePriceDTO> vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
}
