package com.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class VehiclePriceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("MSRP")
    private Double msrp;
    @JsonProperty("Savings")
    private Double savings;
    private Double finalPrice;

    public VehiclePriceDTO() {
    }

    public VehiclePriceDTO(Double msrp, Double savings, Double finalPrice) {
        this.msrp = msrp;
        this.savings = savings;
        this.finalPrice = finalPrice;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
