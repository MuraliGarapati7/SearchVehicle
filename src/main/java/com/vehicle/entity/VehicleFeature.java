package com.vehicle.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicleFeature")
public class VehicleFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Exterior> exterior;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Interior> interior;

    public VehicleFeature() {
    }

    public VehicleFeature(Integer id, List<Exterior> exterior, List<Interior> interior) {
        this.id = id;
        this.exterior = exterior;
        this.interior = interior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Exterior> getExterior() {
        return exterior;
    }

    public void setExterior(List<Exterior> exterior) {
        this.exterior = exterior;
    }

    public List<Interior> getInterior() {
        return interior;
    }

    public void setInterior(List<Interior> interior) {
        this.interior = interior;
    }
}
