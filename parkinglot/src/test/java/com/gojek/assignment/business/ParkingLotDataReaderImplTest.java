package com.gojek.assignment.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gojek.assignment.business.ParkingLotDataReader;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.models.ParkingSlot;
import com.gojek.assignment.objectfactory.ParkingObjectsFactory;

public class ParkingLotDataReaderImplTest {

	private static ParkingLot parkingLot = null;
	private static ParkingLotDataReader parkingLotDataReader;

	@Before
	public void initialize() throws Exception {
		ParkingObjectsFactory.createObjectFactory();
		parkingLotDataReader = (ParkingLotDataReader) ParkingObjectsFactory.getObject("ParkingLotDataReader");
		createParkingMockData();
	}

	private void createParkingMockData() {

		parkingLot = new ParkingLot(6);
		parkingLot.getSlotsMap().put(1, new ParkingSlot(1, "KA-01-HH-1234", "White"));
		parkingLot.getSlotsMap().put(2, new ParkingSlot(2, "KA-01-HH-9999", "White"));
		parkingLot.getSlotsMap().put(3, new ParkingSlot(3, "KA-01-BB-0001", "Black"));
		parkingLot.getSlotsMap().put(4, new ParkingSlot(4, "KA-01-HH-7777", "Red"));
		parkingLot.getSlotsMap().put(5, new ParkingSlot(5, "KA-01-HH-2701", "Blue"));
		parkingLot.getSlotsMap().put(6, new ParkingSlot(6, "KA-01-HH-3141", "Black"));

	}

	@Test
	public void getRegistrationForCarColorSucessTest() {
		ParkingLotResponse response = parkingLotDataReader.getRegistrationForCarColor(parkingLot, "Black");
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getMessage(), "KA-01-BB-0001, KA-01-HH-3141");
	}

	@Test
	public void getRegistrationForCarWrongColorTest() {
		ParkingLotResponse response = parkingLotDataReader.getRegistrationForCarColor(parkingLot, "Black1");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Not found");
	}

	@Test
	public void getRegistrationForCarNullColorTest() {
		ParkingLotResponse response = parkingLotDataReader.getRegistrationForCarColor(parkingLot, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getRegistrationForCarParkingLotNullTest() {
		ParkingLotResponse response = parkingLotDataReader.getRegistrationForCarColor(null, "Black");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getRegistrationForCarParkingLotNoDataTest() {
		ParkingLotResponse response = parkingLotDataReader.getRegistrationForCarColor(null, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getSlotForRegistrationSucessTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForRegistration(parkingLot, "KA-01-BB-0001");
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getMessage(), "3");
	}

	@Test
	public void getSlotForRegistrationWrongNumberTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForRegistration(parkingLot, "KA-01-BB-111");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Not found");
	}

	@Test
	public void getSlotForRegistrationNullNumberTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForRegistration(parkingLot, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getSlotForRegistrationNullParkingLotTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForRegistration(null, "KA-01-BB-111");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getSlotForRegistrationNoDataTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForRegistration(null, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getSlotForColorSucessTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForColor(parkingLot, "White");
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getMessage(), "1, 2");
	}

	@Test
	public void getSlotForColorFailTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForColor(parkingLot, "White1");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Not found");
	}

	@Test
	public void getSlotForColorNullColorTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForColor(parkingLot, null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getSlotForColorNullParkingLotTest() {
		ParkingLotResponse response = parkingLotDataReader.getSlotForColor(null, "White");
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");
	}

	@Test
	public void getStatusSucessTest() {
		ParkingLotResponse response = parkingLotDataReader.getStatus(parkingLot);
		assertEquals(response.getSuccess(), true);
	}

	@Test
	public void getStatusEmptyLotTest() {

		ParkingLotResponse response = parkingLotDataReader.getStatus(new ParkingLot(6));
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Parkinglot is empty");

	}

	@Test
	public void getStatusNullTest() {

		ParkingLotResponse response = parkingLotDataReader.getStatus(null);
		assertEquals(response.getSuccess(), false);
		assertEquals(response.getMessage(), "Input data is invalid");

	}

}
