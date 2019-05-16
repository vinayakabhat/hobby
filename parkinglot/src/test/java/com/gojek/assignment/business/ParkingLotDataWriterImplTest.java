package com.gojek.assignment.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gojek.assignment.business.ParkingLotDataWriter;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.objectfactory.ParkingObjectsFactory;

public class ParkingLotDataWriterImplTest {

	private static ParkingLot parkingLot = null;
	private static ParkingLotDataWriter parkingLotDataWriter;

	@Before
	public void initialize() throws Exception {
		ParkingObjectsFactory.createObjectFactory();
		parkingLotDataWriter = (ParkingLotDataWriter) ParkingObjectsFactory.getObject("ParkingLotDataWriter");
		createParkingMockData();
	}

	private void createParkingMockData() {

		parkingLot = new ParkingLot(5);
	}

	@Test
	public void createParkingLotSucessTest() {
		ParkingLotResponse response = parkingLotDataWriter.createParkingLot(1);
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getParkinglot().getFreeSlotsAvailable().size(), 1);
		assertEquals(response.getMessage(), "Created a parking lot with 1 slots");
	}

	@Test
	public void createParkingLotNegativeSizeTest() {
		ParkingLotResponse response = parkingLotDataWriter.createParkingLot(-1);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void createParkingLotNullTest() {
		ParkingLotResponse response = parkingLotDataWriter.createParkingLot(null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void parkCarSucessTest() {

		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(parkingLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 4);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(parkingLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 3);

	}

	@Test
	public void parkCarParkOverFlowTest() {
		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response3.getSuccess(), false);
		assertEquals(response3.getMessage(), "Sorry, parking lot is full");
		assertEquals(response3.getParkinglot().getFreeSlotsAvailable().size(), 0);

	}

	@Test
	public void parkCarNullTest() {
		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response = parkingLotDataWriter.parkCar(pLot, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");

	}

	@Test
	public void parkCarPlotNullTest() {

		ParkingLotResponse response = parkingLotDataWriter.parkCar(null, "KA-01-HH-9999 White");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}
	
	@Test
	public void parkCarNullArgumentsTest() {

		ParkingLotResponse response = parkingLotDataWriter.parkCar(null, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}
	
	@Test
	public void removeCarSucessTest() {

		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.removeCar(pLot, 1);
		assertEquals(response3.getSuccess(), true);
		assertEquals(response3.getMessage(), "Slot number 1 is free");
		assertEquals(response3.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response4 = parkingLotDataWriter.removeCar(pLot, 2);
		assertEquals(response4.getSuccess(), true);
		assertEquals(response4.getMessage(), "Slot number 2 is free");
		assertEquals(response4.getParkinglot().getFreeSlotsAvailable().size(), 2);
		
		
	}
	
	@Test
	public void removeCarInvalidInputTest() {

		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.removeCar(pLot, 4);
		assertEquals(response3.getSuccess(), false);
		assertEquals(response3.getMessage(), "Enter valid slot number");
		assertEquals(response3.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response4 = parkingLotDataWriter.removeCar(pLot, -2);
		assertEquals(response4.getSuccess(), false);
		assertEquals(response4.getMessage(), "Enter valid slot number");
		assertEquals(response4.getParkinglot().getFreeSlotsAvailable().size(), 0);
		
		
	}
	
	@Test
	public void removeRemovedCarTest() {

		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.removeCar(pLot, 2);
		assertEquals(response3.getSuccess(), true);
		assertEquals(response3.getMessage(), "Slot number 2 is free");
		assertEquals(response3.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response4 = parkingLotDataWriter.removeCar(pLot, 2);
		assertEquals(response4.getSuccess(), false);
		assertEquals(response4.getMessage(), "Slot is free already");
		assertEquals(response4.getParkinglot().getFreeSlotsAvailable().size(), 1);
		
		
	}
	
	@Test
	public void removeRemovedCarNullInputTest() {

		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.removeCar(pLot, null);
		assertEquals(response3.getSuccess(), false);
		assertEquals(response3.getMessage(), "Enter valid slot number");
		ParkingLotResponse response4 = parkingLotDataWriter.removeCar(null, 1);
		assertEquals(response4.getSuccess(), false);
		assertEquals(response4.getMessage(), "Input data is invalid");
		ParkingLotResponse response5 = parkingLotDataWriter.removeCar(null, null);
		assertEquals(response5.getSuccess(), false);
		assertEquals(response5.getMessage(), "Input data is invalid");
		
		
	}
	
	@Test
	public void removeRemovedInputRangeTest() {

		ParkingLot pLot = parkingLotDataWriter.createParkingLot(2).getParkinglot();
		ParkingLotResponse response1 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-1234 White");
		assertEquals(response1.getSuccess(), true);
		assertEquals(response1.getMessage(), "Allocated slot number: 1");
		assertEquals(response1.getParkinglot().getFreeSlotsAvailable().size(), 1);
		ParkingLotResponse response2 = parkingLotDataWriter.parkCar(pLot, "KA-01-HH-9999 White");
		assertEquals(response2.getSuccess(), true);
		assertEquals(response2.getMessage(), "Allocated slot number: 2");
		assertEquals(response2.getParkinglot().getFreeSlotsAvailable().size(), 0);
		ParkingLotResponse response3 = parkingLotDataWriter.removeCar(pLot, 3);
		assertEquals(response3.getSuccess(), false);
		assertEquals(response3.getMessage(), "Enter valid slot number");
		ParkingLotResponse response4 = parkingLotDataWriter.removeCar(pLot, -1);
		assertEquals(response4.getSuccess(), false);
		assertEquals(response4.getMessage(), "Enter valid slot number");
		
	}

}
