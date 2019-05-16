package com.gojek.assignment.service;

import com.gojek.assignment.business.ParkingLotDataReader;
import com.gojek.assignment.business.ParkingLotDataWriter;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.objectfactory.ParkingObjectsFactory;

public class ParkingServiceImpl implements ParkingService {

	private static ParkingLotDataReader parkingLotDataReader = (ParkingLotDataReader) ParkingObjectsFactory
			.getObject("ParkingLotDataReader");
	private static ParkingLotDataWriter parkingLotDataWriter = (ParkingLotDataWriter) ParkingObjectsFactory
			.getObject("ParkingLotDataWriter");

	@Override
	public ParkingLotResponse createParkingLot(Integer size) {

		return parkingLotDataWriter.createParkingLot(size);
	}

	@Override
	public ParkingLotResponse parkCar(ParkingLot parkingLot, String carDetails) {
		
		return parkingLotDataWriter.parkCar(parkingLot, carDetails);
		
	} 

	@Override
	public ParkingLotResponse removeCar(ParkingLot parkingLot, Integer slotNumber) {
		return parkingLotDataWriter.removeCar(parkingLot, slotNumber);
	}

	@Override
	public ParkingLotResponse getRegistrationForCarColor(ParkingLot parkingLot, String color) {
		return parkingLotDataReader.getRegistrationForCarColor(parkingLot,color);
	}

	@Override
	public ParkingLotResponse getSlotForRegistration(ParkingLot parkingLot, String carNumber) {
		return parkingLotDataReader.getSlotForRegistration(parkingLot,carNumber);
	}

	@Override
	public ParkingLotResponse getSlotForColor(ParkingLot parkingLot, String color) {
		return parkingLotDataReader.getSlotForColor(parkingLot,color);
	}

	@Override
	public ParkingLotResponse getStatus(ParkingLot parkingLot) {
		return parkingLotDataReader.getStatus(parkingLot);
	}

}
