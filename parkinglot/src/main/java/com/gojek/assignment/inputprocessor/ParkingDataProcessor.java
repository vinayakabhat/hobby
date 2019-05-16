package com.gojek.assignment.inputprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.gojek.assignment.models.Constants;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.objectfactory.ParkingObjectsFactory;
import com.gojek.assignment.service.ParkingService;

public class ParkingDataProcessor {

	private static final Logger logger = Logger.getLogger("ParkingDataProcessor");
	private static ParkingService parkingService = (ParkingService) ParkingObjectsFactory.getObject("ParkingService");
	private static ParkingLotResponse parkingLotResponse = null;
	private static ParkingLot parkingLot = null;

	// Entry point
	public static Boolean startSystem() {
		// reads input data
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				// send data for processing
				ProcessData(line);
				
			}
		} catch (Exception e) {
			logger.info("Exception" + e.getMessage());
		}
		return true;
	}

	public static ParkingLotResponse ProcessData(String inputdata) throws Exception {

		String[] input = inputdata.split(" ");
		String inputType = input[0];

		// commands other than exit and status should have space separated strings

		if (input.length < 2 && !Constants.STATUS.equals(inputType) && !Constants.EXIT.equals(inputType)) {
			System.out.println("Please enter valid input!");
			return new ParkingLotResponse(parkingLot,"Please enter valid input!",false);
		}

		switch (inputType) {
		case Constants.READ_FILE:
			// read the input from a file
			FileReader file = new FileReader(input[1].trim());
			String line;
			BufferedReader bufferedReader = new BufferedReader(file);
			while ((line = bufferedReader.readLine()) != null) {
				ParkingDataProcessor.ProcessData(line);
			}
			file.close();
			break;
		case Constants.CREATE_LOT:
			int size = Integer.parseInt(input[1].trim());
			parkingLotResponse = parkingService.createParkingLot(size);
			parkingLot = parkingLotResponse.getParkinglot();
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.PARK:
			parkingLotResponse = parkingService.parkCar(parkingLot, input[1] + Constants.SPACE + input[2]);
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.LEAVE:
			parkingLotResponse = parkingService.removeCar(parkingLot, Integer.parseInt(input[1]));
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.REGISTRATION_NUMBERS_FOR_CARS_COLOR:
			parkingLotResponse = parkingService.getRegistrationForCarColor(parkingLot, input[1]);
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
			parkingLotResponse = parkingService.getSlotForRegistration(parkingLot, input[1]);
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOR:
			parkingLotResponse = parkingService.getSlotForColor(parkingLot, input[1]);
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.STATUS:
			parkingLotResponse = parkingService.getStatus(parkingLot);
			System.out.println(parkingLotResponse.getMessage());
			break;

		case Constants.EXIT:
			System.exit(0);
			break;

		default:
			parkingLotResponse =  new ParkingLotResponse(parkingLot,"Invalid operation!",false);
			System.out.println(parkingLotResponse.getMessage());
		}
		return parkingLotResponse;
	}

}
