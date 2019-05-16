package com.gojek.assignment.inputprocessor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gojek.assignment.objectfactory.ParkingObjectsFactory;

public class ParkingDataProcessorTest {

	@Before
	public void initialize() throws Exception {
		ParkingObjectsFactory.createObjectFactory();

	}

	@Test
	public void createParkingLotTest() throws Exception {

		assertEquals(ParkingDataProcessor.ProcessData("create_parking_lot 3").getMessage(),
				"Created a parking lot with 3 slots");
	}

	@Test
	public void parkCarTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		assertEquals(ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White").getMessage(),
				"Allocated slot number: 1");
		assertEquals(ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red").getMessage(),
				"Allocated slot number: 2");
		assertEquals(ParkingDataProcessor.ProcessData("park KA-03-HH-9999 White").getMessage(),
				"Allocated slot number: 3");

	}

	@Test
	public void removeCarTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9999 White");
		assertEquals(ParkingDataProcessor.ProcessData("leave 1").getMessage(), "Slot number 1 is free");
		assertEquals(ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White").getMessage(),
				"Allocated slot number: 1");
	}

	@Test
	public void getRegistrationForCarColorTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9988 White");
		assertEquals(ParkingDataProcessor.ProcessData("registration_numbers_for_cars_with_colour White").getMessage(),
				"KA-01-HH-9999, KA-03-HH-9988");
	}

	@Test
	public void getSlotForRegistrationTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9988 White");
		assertEquals(ParkingDataProcessor.ProcessData("slot_numbers_for_cars_with_colour White").getMessage(), "1, 3");
	}

	@Test
	public void getStatusTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9988 White");
		assertEquals(ParkingDataProcessor.ProcessData("status").getSuccess(), true);
	}

	@Test
	public void getSlotForColorTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9988 White");
		assertEquals(ParkingDataProcessor.ProcessData("slot_number_for_registration_number KA-01-HH-9999").getMessage(),
				"1");
	}

	@Test
	public void InvalidInputTest() throws Exception {
		ParkingDataProcessor.ProcessData("create_parking_lot 3");
		ParkingDataProcessor.ProcessData("park KA-01-HH-9999 White");
		ParkingDataProcessor.ProcessData("park KA-02-HH-9999 Red");
		ParkingDataProcessor.ProcessData("park KA-03-HH-9988 White");
		assertEquals(ParkingDataProcessor.ProcessData("Hello").getMessage(), "Please enter valid input!");
		assertEquals(ParkingDataProcessor.ProcessData("slot KA-01-HH-9999").getMessage(), "Invalid operation!");
		assertEquals(ParkingDataProcessor.ProcessData("Hello world").getMessage(), "Invalid operation!");
		assertEquals(ParkingDataProcessor.ProcessData("Hello world hello").getMessage(), "Invalid operation!");

	}

}
