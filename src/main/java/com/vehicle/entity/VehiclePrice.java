package com.vehicle.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehiclePrice")
public class VehiclePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double msrp;
    private Double savings;
    private Double finalPrice;

    public VehiclePrice() {
    }

    public VehiclePrice(Integer id, Double msrp, Double savings, Double finalPrice) {
        this.id = id;
        this.msrp = msrp;
        this.savings = savings;
        this.finalPrice = finalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
