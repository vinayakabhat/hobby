package com.gojek.assignment.service;

import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;

/**
 * @author Vinayaka K R
 * Interface providing car services 
 *
 */
public interface ParkingService {

	/**
	 * API to create parking lot
	 * 
	 * @param size - size of parking lot to create
	 * @return - ParkingLotResponse object
	 */
	public ParkingLotResponse createParkingLot(Integer size);

	/**
	 * API to park a car in parkingLot(parking logic is inside its implementation )
	 * 
	 * @param parkingLot - ParkingLot object
	 * @param carDetails - car details
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse parkCar(ParkingLot parkingLot, String carDetails);

	/**
	 * API to remove a car from given slot
	 * 
	 * @param parkingLot - parkingLot object
	 * @param slotNumber - slot number
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse removeCar(ParkingLot parkingLot, Integer slotNumber);

	/**
	 * API to get car details by car color 
	 * @param parkingLot
	 * @param color - car color
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse getRegistrationForCarColor(ParkingLot parkingLot, String color);

	/**
	 * API to get the slot details by car number 
	 * @param parkingLot
	 * @param carNumber
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse getSlotForRegistration(ParkingLot parkingLot, String carNumber);

	/**
	 * API to get allocated slots for given car color 
	 * @param parkingLot
	 * @param color
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse getSlotForColor(ParkingLot parkingLot, String color);

	/**
	 * API to know parking lot status
	 * @param parkingLot
	 * @return ParkingLotResponse - ParkingLotResponse Object
	 */
	public ParkingLotResponse getStatus(ParkingLot parkingLot);


}
