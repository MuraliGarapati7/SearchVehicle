package com.vehicle.repository;

import com.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByVehicleDetails_model(String model);

    List<Vehicle> findByVehicleDetails_VehiclePrice_finalPriceBetween(Double from, Double to);

    List<Vehicle> findByVehicleDetails_VehicleFeature_Exterior_valueContainingIgnoreCaseAndVehicleDetails_VehicleFeature_Interior_valueContainingIgnoreCase(String exteriorValue, String interiorValue);
}
