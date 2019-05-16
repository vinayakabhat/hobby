package com.gojek.assignment.business;

import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;

public interface ParkingLotDataWriter {

	public ParkingLotResponse createParkingLot(Integer size);

	public ParkingLotResponse parkCar(ParkingLot parkingLot, String carDetails);

	public ParkingLotResponse removeCar(ParkingLot parkingLot, Integer slotNumber);

}
