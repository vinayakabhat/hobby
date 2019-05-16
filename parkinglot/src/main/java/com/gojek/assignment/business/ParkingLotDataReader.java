package com.gojek.assignment.business;

import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;

public interface ParkingLotDataReader {

	ParkingLotResponse getRegistrationForCarColor(ParkingLot parkingLot, String color);

	ParkingLotResponse getSlotForRegistration(ParkingLot parkingLot, String carNumber);

	ParkingLotResponse getSlotForColor(ParkingLot parkingLot, String color);

	ParkingLotResponse getStatus(ParkingLot parkingLot);

	
}
