package com.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.vehicle.dto.RequestData;
import com.vehicle.dto.ResponseData;
import com.vehicle.dto.VehicleDTO;
import com.vehicle.dto.VehicleDetailsDTO;
import com.vehicle.dto.VehicleFeatureDTO;
import com.vehicle.dto.VehicleListDTO;
import com.vehicle.dto.VehiclePriceDTO;
import com.vehicle.entity.Exterior;
import com.vehicle.entity.Interior;
import com.vehicle.entity.Vehicle;
import com.vehicle.entity.VehicleDetails;
import com.vehicle.entity.VehicleFeature;
import com.vehicle.entity.VehiclePrice;
import com.vehicle.repository.VehicleRepository;
import com.vehicle.service.VehicleService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleServiceTest {
	
	@Mock
	private VehicleRepository vehicleRepository;
	
	VehicleService service;
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new VehicleService();
		ReflectionTestUtils.setField(service, "vehicleRepository", vehicleRepository);
	}

	@Test
	public void testSubmitVehicle() {
		final ArgumentCaptor<Vehicle> captor = ArgumentCaptor.forClass(Vehicle.class);
		ResponseData response  = service.submitVehicle(getVehicle());
		verify(vehicleRepository).save(captor.capture()); 
		List<Vehicle> actual = captor.getAllValues();
		verifyVehicle(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)), actual.get(0));
		assertEquals("{"+actual.get(0).getVehicleId() + "}submitted to database successfully",response.getMessage());
	}
	
	@Test
	public void testSubmitVehicleEmptyVehicleDetails() {
		RequestData request = getVehicle();
		request.setVehicles(null);
		ResponseData response  = service.submitVehicle(request);
		assertEquals("FAILED",response.getStatus());
		assertEquals(204, response.getStatusCode());
	}
	
	@Test
	public void testSubmitVehicleEmptyCollection() {
		RequestData request = getVehicle();
		request.getVehicles().setVehicle(new ArrayList<>());
		ResponseData response  = service.submitVehicle(request);
		assertEquals("FAILED",response.getStatus());
		assertEquals(204, response.getStatusCode());
	}
	
	@Test
	public void testSubmitVehicleNullCollection() {
		RequestData request = getVehicle();
		request.getVehicles().setVehicle(null);
		ResponseData response  = service.submitVehicle(request);
		assertEquals("FAILED",response.getStatus());
		assertEquals(204, response.getStatusCode());
	}
	
	@Test
	public void testGetVehicleInformation() {
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)));
		Mockito.lenient().when(vehicleRepository.findAll()).thenReturn(vehicleList);
		ResponseData response  = service.getVehicleInformation();
		verify(vehicleRepository).findAll(); 
		assertEquals("Fetched all vehicles from database successfully",response.getMessage());
		verifyVehicle(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)), response.getVehicles().getVehicle().get(0).getVehicleDetails());
	}

	@Test
	public void testGetVehicleInformationByModelName() {
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)));
		String modelName = "City";
		Mockito.lenient().when(vehicleRepository.findByVehicleDetails_model(modelName)).thenReturn(vehicleList);
		ResponseData response  = service.getVehicleInformationByModelName(modelName);
		verify(vehicleRepository).findByVehicleDetails_model(modelName); 
		assertEquals("Fetched all vehicles from database successfully",response.getMessage());
		verifyVehicle(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)), response.getVehicles().getVehicle().get(0).getVehicleDetails());
	}

	@Test
	public void testGetVehicleInformationByPriceRange() {
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)));
		Mockito.lenient().when(vehicleRepository.findByVehicleDetails_VehiclePrice_finalPriceBetween(Double.parseDouble("20000.00"),
				Double.parseDouble("50000.00"))).thenReturn(vehicleList);
		ResponseData response  = service.getVehicleInformationByPriceRange("20000.00", "50000.00");
		verify(vehicleRepository).findByVehicleDetails_VehiclePrice_finalPriceBetween(Double.parseDouble("20000.00"), Double.parseDouble("50000.00")); 
		assertEquals("Fetched all vehicles from database successfully",response.getMessage());
		verifyVehicle(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)), response.getVehicles().getVehicle().get(0).getVehicleDetails());
	}
	
	@Test
	public void testGetVehicleInformationByFeatures() {
		String exterior = "Beltline Molding - Black";
		String interior = "60/40 Split Fold Rear Seat";
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)));

		Mockito.lenient().when(vehicleRepository.findByVehicleDetails_VehicleFeature_Exterior_valueContainingIgnoreCaseAndVehicleDetails_VehicleFeature_Interior_valueContainingIgnoreCase(exterior,
				interior)).thenReturn(vehicleList);
		ResponseData response  = service.getVehicleInformationByFeatures(exterior, interior);
		
		verify(vehicleRepository).findByVehicleDetails_VehicleFeature_Exterior_valueContainingIgnoreCaseAndVehicleDetails_VehicleFeature_Interior_valueContainingIgnoreCase(exterior,
				interior); 
		assertEquals("Fetched all vehicles from database successfully",response.getMessage());
		verifyVehicle(getExpectedoutput(getVehicle().getVehicles().getVehicle().get(0)), response.getVehicles().getVehicle().get(0).getVehicleDetails());
	}
	
	@Test
	public void testGetVehicleInformationByFeaturesWhichReturnsZeroData() {
		String exterior = "Beltline Molding - Black";
		String interior = "60/40 Split Fold Rear Seat";
		Mockito.lenient().when(vehicleRepository.findByVehicleDetails_VehicleFeature_Exterior_valueContainingIgnoreCaseAndVehicleDetails_VehicleFeature_Interior_valueContainingIgnoreCase(exterior,
				interior)).thenReturn(new ArrayList<>());
		ResponseData response  = service.getVehicleInformationByFeatures(exterior, interior);
		assertEquals("Fetched all vehicles from database successfully",response.getMessage());
		assertEquals(new ArrayList<>(), response.getVehicles().getVehicle());
	}
	
	private void verifyVehicle(Vehicle expectedoutput, VehicleDetailsDTO actual) {
		assertEquals(expectedoutput.getVehicleDetails().getBodyStyle(), actual.getBodyStyle());
		assertEquals(expectedoutput.getVehicleDetails().getMake(), actual.getMake());
		assertEquals(expectedoutput.getVehicleDetails().getModel(), actual.getModel());
		assertEquals(expectedoutput.getVehicleDetails().getModelYear(), actual.getModelYear());
		assertEquals(expectedoutput.getVehicleDetails().getEngine(), actual.getEngine());
		assertEquals(expectedoutput.getVehicleDetails().getDrivetype(), actual.getDrivetype());
		assertEquals(expectedoutput.getVehicleDetails().getColor(), actual.getColor());
		assertEquals(expectedoutput.getVehicleDetails().getMpg(), actual.getMpg());
	}

	private void verifyVehicle(Vehicle expectedoutput, Vehicle actual) {
		assertEquals(expectedoutput.getVehicleId(), actual.getVehicleId());
		assertEquals(expectedoutput.getVehicleDetails().getBodyStyle(), actual.getVehicleDetails().getBodyStyle());
		assertEquals(expectedoutput.getVehicleDetails().getMake(), actual.getVehicleDetails().getMake());
		assertEquals(expectedoutput.getVehicleDetails().getModel(), actual.getVehicleDetails().getModel());
		assertEquals(expectedoutput.getVehicleDetails().getModelYear(), actual.getVehicleDetails().getModelYear());
		assertEquals(expectedoutput.getVehicleDetails().getEngine(), actual.getVehicleDetails().getEngine());
		assertEquals(expectedoutput.getVehicleDetails().getDrivetype(), actual.getVehicleDetails().getDrivetype());
		assertEquals(expectedoutput.getVehicleDetails().getColor(), actual.getVehicleDetails().getColor());
		assertEquals(expectedoutput.getVehicleDetails().getMpg(), actual.getVehicleDetails().getMpg());
	}


	private Vehicle getExpectedoutput(VehicleDTO vehicleDTO) {
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
                 vehicleDTO.getVehicleDetails().getDrivetype(), vehicleDTO.getVehicleDetails().getColor(), vehicleDTO.getVehicleDetails().getMpg(), 
                 vehicleFeature, vehiclePrice);
         Vehicle vehicleDB = new Vehicle(vehicleDTO.getVehicleId(), vehicleDetails);
		return vehicleDB;
	}

	private RequestData getVehicle() {
		RequestData data = new RequestData();
		VehicleListDTO vehicles = new VehicleListDTO();
		List<VehicleDTO> vehicle = new ArrayList<>();
		
		VehicleDTO vehicle1 = new VehicleDTO();
		vehicle1.setVehicleId(1);
		
		VehicleDetailsDTO vehicleDetails = generateVehicleDetailsDTO();
		ArrayList<VehiclePriceDTO> vehiclePrice = new ArrayList<>();
		
		VehiclePriceDTO price1 = new VehiclePriceDTO();
		price1.setFinalPrice(new Double(30000.00));
		price1.setMsrp(new Double(50000.00));
		price1.setSavings(new Double(20000.00));
		
		VehiclePriceDTO price2 = new VehiclePriceDTO();
		price2.setFinalPrice(new Double(30000.00));
		price2.setMsrp(new Double(50000.00));
		price2.setSavings(new Double(20000.00));
		
		VehiclePriceDTO price3 = new VehiclePriceDTO();
		price3.setFinalPrice(new Double(30000.00));
		price3.setMsrp(new Double(50000.00));
		price3.setSavings(new Double(20000.00));
		
		vehiclePrice.add(price1);
		vehiclePrice.add(price2);
		vehiclePrice.add(price3);
		vehicleDetails.setVehiclePrice(vehiclePrice);
		
		vehicle1.setVehicleDetails(vehicleDetails);
		vehicle.add(vehicle1);
		
		vehicles.setVehicle(vehicle);
		data.setVehicles(vehicles);
		return data;
	}
	
	private VehicleDetailsDTO generateVehicleDetailsDTO() {
		VehicleDetailsDTO vehicleDetails = new VehicleDetailsDTO();
		vehicleDetails.setBodyStyle("4D Sport Utility");
		vehicleDetails.setColor("Agate Black");
		vehicleDetails.setDrivetype("AWD");
		vehicleDetails.setMake("Ford");
		vehicleDetails.setModel("Edge");
		vehicleDetails.setModelYear("2019");
		vehicleDetails.setEngine("Twin-Scroll 2.0L EcoBoost");
		vehicleDetails.setMpg("28");
		VehicleFeatureDTO vehicleFeature = new VehicleFeatureDTO();
		List<String> exterior = new ArrayList<>();
		exterior.add("Beltline Molding - Black");
		exterior.add("Door Handles - Body Color");
		exterior.add("Grille - Chrome");
		exterior.add("Taillamps-Led");
		vehicleFeature.setExterior(exterior);
		List<String> interior = new ArrayList<>();
		interior.add("60/40 Split Fold Rear Seat");
		interior.add("Cruise Control");
		interior.add("Dual Illum Vis Vanity Mirr");
		interior.add("Rotary Gear Shift Dial");
		vehicleFeature.setInterior(interior);
		vehicleDetails.setVehicleFeature(vehicleFeature);
		
		return vehicleDetails;
	}
}
