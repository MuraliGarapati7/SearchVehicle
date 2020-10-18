package com.vehicle.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle_details")
public class VehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String make;
    private String model;
    private String modelYear;
    private String bodyStyle;
    private String engine;
    private String drivetype;
    private String color;
    private String mpg;

    @ManyToOne(cascade = {CascadeType.ALL})
    private VehicleFeature vehicleFeature;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<VehiclePrice> vehiclePrice;

    public VehicleDetails() {
    }

    public VehicleDetails(Integer id, String make, String model, String modelYear, String bodyStyle, String engine, String drivetype, String color, String mpg, VehicleFeature vehicleFeature, List<VehiclePrice> vehiclePrice) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public VehicleFeature getVehicleFeature() {
        return vehicleFeature;
    }

    public void setVehicleFeature(VehicleFeature vehicleFeature) {
        this.vehicleFeature = vehicleFeature;
    }

    public List<VehiclePrice> getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(List<VehiclePrice> vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
}
