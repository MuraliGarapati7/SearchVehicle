package com.vehicle.controller;

import com.vehicle.dto.RequestData;
import com.vehicle.dto.ResponseData;
import com.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicleInformation/submitVehicle")
    public ResponseData submitVehicle(@RequestBody RequestData vehicle) {
        return vehicleService.submitVehicle(vehicle);
    }

    @GetMapping("/getVehicleInformation")
    public ResponseData getVehicleInformation() {
        return vehicleService.getVehicleInformation();
    }

    @GetMapping("/getVehicleModelName/{modelName}")
    public ResponseData getVehicleInformationByModelName(@PathVariable("modelName") String modelName) {
        return vehicleService.getVehicleInformationByModelName(modelName);
    }

    @GetMapping("/getVehiclePrice/{From}/{TO}")
    public ResponseData getVehicleInformationByPriceRange(@PathVariable("From") String from, @PathVariable("TO") String to) {
        return vehicleService.getVehicleInformationByPriceRange(from, to);
    }

    @GetMapping("getVehicleFeature/{exterior}/{interior}")
    public ResponseData getVehicleInformationByFeatures(@PathVariable("exterior") String exterior, @PathVariable("interior") String interior) {
        ResponseData response = new ResponseData();
        if (exterior.length() < 3) {
            response.setStatus("fail");
            response.setMessage("Exterior value length should be greater than 3.");
            return response;
        }
        if (interior.length() < 3) {
            response.setStatus("fail");
            response.setMessage("Interior value length should be greater than 3.");
            return response;
        }
        return vehicleService.getVehicleInformationByFeatures(exterior, interior);
    }


}