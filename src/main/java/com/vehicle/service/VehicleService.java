package com.vehicle.service;

import com.vehicle.dto.*;
import com.vehicle.entity.*;
import com.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseData submitVehicle(RequestData vehicle) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus("OK");
        responseData.setStatusCode(200);
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        if (vehicle.getVehicles() != null && !CollectionUtils.isEmpty(vehicle.getVehicles().getVehicle())) {
            List<String> vehicleIds = new ArrayList<>();
            VehicleListDTO str = vehicle.getVehicles();
            List<VehicleDTO> str1 = str.getVehicle();
            System.out.println(str1.isEmpty());
            str1.forEach(vehicleDTO -> {
                ArrayList<Exterior> exterior = new ArrayList<>();
                ArrayList<Interior> interior = new ArrayList<>();
                vehicleDTO.getVehicleDetails().getVehicleFeature().getExterior().forEach(ext -> {
                    exterior.add(new Exterior(null, ext));
                });
                vehicleDTO.getVehicleDetails().getVehicleFeature().getInterior().forEach(inte -> {
                    interior.add(new Interior(null, inte));
                });
                VehicleFeature vehicleFeature = new VehicleFeature(null, exterior, interior);
                ArrayList<VehiclePrice> vehiclePrice = new ArrayList<>();
                vehicleDTO.getVehicleDetails().getVehiclePrice().forEach(vp -> {
                    vehiclePrice.add(new VehiclePrice(null, vp.getMsrp(), vp.getSavings(), vp.getFinalPrice()));
                });
                VehicleDetails vehicleDetails = new VehicleDetails(null, vehicleDTO.getVehicleDetails().getMake(), vehicleDTO.getVehicleDetails().getModel(),
                        vehicleDTO.getVehicleDetails().getModelYear(), vehicleDTO.getVehicleDetails().getBodyStyle(), vehicleDTO.getVehicleDetails().getEngine(),
                        vehicleDTO.getVehicleDetails().getDrivetype(), vehicleDTO.getVehicleDetails().getColor(), vehicleDTO.getVehicleDetails().getMpg(), vehicleFeature, vehiclePrice);
                Vehicle vehicleDB = new Vehicle(vehicleDTO.getVehicleId(), vehicleDetails);
                vehicleRepository.save(vehicleDB);
                vehicleDTOS.add(vehicleToVehicleDTO(vehicleDB));
                vehicleIds.add(vehicleDB.getVehicleId().toString());
            });
            responseData.setVehicles(new VehicleListDTO(vehicleDTOS));
            responseData.setMessage("{" + String.join(",", vehicleIds) + "}" + "submitted to database successfully");
        } else {
            responseData.setStatus("FAILED");
            responseData.setStatusCode(HttpStatus.NO_CONTENT.value());
        }
        return responseData;

    }

    public ResponseData getVehicleInformation() {
        ResponseData responseData = new ResponseData();
        responseData.setStatus("OK");
        responseData.setStatusCode(200);
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vehicleRepository.findAll();
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicleList.forEach(vehicle -> {
            vehicleDTOS.add(vehicleToVehicleDTO(vehicle));
        });
        responseData.setVehicles(new VehicleListDTO(vehicleDTOS));
        responseData.setMessage("Fetched all vehicles from database successfully");
        return responseData;
    }

    public ResponseData getVehicleInformationByModelName(String modelName) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus("OK");
        responseData.setStatusCode(200);
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vehicleRepository.findByVehicleDetails_model(modelName);
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicleList.forEach(vehicle -> {
            vehicleDTOS.add(vehicleToVehicleDTO(vehicle));
        });
        responseData.setVehicles(new VehicleListDTO(vehicleDTOS));
        responseData.setMessage("Fetched all vehicles from database successfully");
        return responseData;
    }

    public ResponseData getVehicleInformationByPriceRange(String from, String to) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus("OK");
        responseData.setStatusCode(200);
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vehicleRepository.findByVehicleDetails_VehiclePrice_finalPriceBetween(Double.parseDouble(from), Double.parseDouble(to));
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicleList.forEach(vehicle -> {
            vehicleDTOS.add(vehicleToVehicleDTO(vehicle));
        });
        responseData.setVehicles(new VehicleListDTO(vehicleDTOS));
        responseData.setMessage("Fetched all vehicles from database successfully");
        return responseData;
    }

    //todo error handling
    public ResponseData getVehicleInformationByFeatures(String exterior, String interior) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus("OK");
        responseData.setStatusCode(200);
        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) vehicleRepository.findByVehicleDetails_VehicleFeature_Exterior_valueContainingIgnoreCaseAndVehicleDetails_VehicleFeature_Interior_valueContainingIgnoreCase(exterior, interior);
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicleList.forEach(vehicle -> {
            vehicleDTOS.add(vehicleToVehicleDTO(vehicle));
        });
        responseData.setVehicles(new VehicleListDTO(vehicleDTOS));
        responseData.setMessage("Fetched all vehicles from database successfully");
        return responseData;
    }

    private VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        ArrayList<String> interior = new ArrayList<>();
        ArrayList<String> exterior = new ArrayList<>();
        vehicle.getVehicleDetails().getVehicleFeature().getInterior().forEach(inter -> {
            interior.add(inter.getValue());
        });
        vehicle.getVehicleDetails().getVehicleFeature().getExterior().forEach(ext -> {
            exterior.add(ext.getValue());
        });
        VehicleFeatureDTO vehicleFeatureDTO = new VehicleFeatureDTO(exterior, interior);
        ArrayList<VehiclePriceDTO> vehiclePriceDTO = new ArrayList<>();
        vehicle.getVehicleDetails().getVehiclePrice().forEach(vp -> {
            vehiclePriceDTO.add(new VehiclePriceDTO(vp.getMsrp(), vp.getSavings(), vp.getFinalPrice()));
        });
        VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO(vehicle.getVehicleDetails().getMake(),
                vehicle.getVehicleDetails().getModel(), vehicle.getVehicleDetails().getModelYear(), vehicle.getVehicleDetails().getBodyStyle(),
                vehicle.getVehicleDetails().getEngine(), vehicle.getVehicleDetails().getDrivetype(), vehicle.getVehicleDetails().getColor(),
                vehicle.getVehicleDetails().getMpg(), vehicleFeatureDTO, vehiclePriceDTO);
        return new VehicleDTO(vehicle.getVehicleId(), vehicleDetailsDTO);
    }

}
