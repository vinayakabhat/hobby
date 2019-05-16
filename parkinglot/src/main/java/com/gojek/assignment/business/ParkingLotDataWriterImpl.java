package com.gojek.assignment.business;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import com.gojek.assignment.models.Constants;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.models.ParkingSlot;

public class ParkingLotDataWriterImpl implements ParkingLotDataWriter {

	@Override
	public ParkingLotResponse createParkingLot(Integer size) {
		if (size != null && size > 0) {
			return new ParkingLotResponse(new ParkingLot(size), "Created a parking lot with " + size + " slots", true);
		} else {
			return new ParkingLotResponse(new ParkingLot(1), "Input data is invalid", false);
		}
	}

	@Override
	public ParkingLotResponse parkCar(ParkingLot parkingLot, String carDetails) {
		if (isInValid(parkingLot, carDetails)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Queue<Integer> freeSlotsQueue = parkingLot.getFreeSlotsAvailable();
		Map<Integer, ParkingSlot> slotsMap = parkingLot.getSlotsMap();

		if (freeSlotsQueue.size() <= 0) {
			return new ParkingLotResponse(parkingLot, "Sorry, parking lot is full", false);
		}

		else {
			ParkingSlot parkingSlot = new ParkingSlot();
			Integer slotId = freeSlotsQueue.remove();
			String[] input = carDetails.split(Constants.SPACE);
			String regNumber = input[0];
			String color = input[1];
			parkingSlot.setCarNumber(regNumber);
			parkingSlot.setCarColor(color);
			parkingSlot.setSlotId(slotId);
			slotsMap.put(slotId, parkingSlot);
			parkingLot.setFreeSlotsAvailable(freeSlotsQueue);
			parkingLot.setSlotsMap(slotsMap);
			return new ParkingLotResponse(parkingLot, "Allocated slot number: " + slotId, true);
		}

	}

	@Override
	public ParkingLotResponse removeCar(ParkingLot parkingLot, Integer slotNumber) {
		if (isInValid(parkingLot, Constants.DUMMY)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Map<Integer, ParkingSlot> parkingMap = parkingLot.getSlotsMap();
		if (slotNumber == null || slotNumber > parkingLot.getSize() || slotNumber < 0) {
			return new ParkingLotResponse(parkingLot, "Enter valid slot number", false);
		} else if (!parkingMap.containsKey(slotNumber)) {
			return new ParkingLotResponse(parkingLot, "Slot is free already", false);
		} else {
			parkingMap.remove(slotNumber);
			parkingLot.getFreeSlotsAvailable().add(slotNumber);
			return new ParkingLotResponse(parkingLot, "Slot number " + slotNumber + " is free", true);
		}
	}

	private boolean isInValid(ParkingLot parkingLot, String data) {
		if (Objects.isNull(parkingLot) || Objects.isNull(data)) {
			return true;
		}
		return false;
	}

}
  