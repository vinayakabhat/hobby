package com.gojek.assignment.business;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.gojek.assignment.models.Constants;
import com.gojek.assignment.models.ParkingLot;
import com.gojek.assignment.models.ParkingLotResponse;
import com.gojek.assignment.models.ParkingSlot;

public class ParkingLotDataReaderImpl implements ParkingLotDataReader {

	@Override
	public ParkingLotResponse getRegistrationForCarColor(ParkingLot parkingLot, String color) {
		if (isInValid(parkingLot, color)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Collection<ParkingSlot> parkingList = parkingLot.getSlotsMap().values();
		String carNums = parkingList.stream().filter(slot -> color.equals(slot.getCarColor()))
				.map(slot -> slot.getCarNumber()).collect(Collectors.joining(", "));
		return sendResponse(parkingLot, carNums);

	}

	@Override
	public ParkingLotResponse getSlotForRegistration(ParkingLot parkingLot, String carNumber) {
		if (isInValid(parkingLot, carNumber)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Collection<ParkingSlot> parkingList = parkingLot.getSlotsMap().values();
		String carSlot = parkingList.stream().filter(slot -> carNumber.equals(slot.getCarNumber()))
				.map(slot -> String.valueOf(slot.getSlotId())).collect(Collectors.joining(", "));
		return sendResponse(parkingLot, carSlot);
	}

	@Override
	public ParkingLotResponse getSlotForColor(ParkingLot parkingLot, String color) {
		if (isInValid(parkingLot, color)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Collection<ParkingSlot> parkingList = parkingLot.getSlotsMap().values();
		String carSlot = parkingList.stream().filter(slot -> color.equals(slot.getCarColor()))
				.map(slot -> String.valueOf(slot.getSlotId())).collect(Collectors.joining(", "));
		return sendResponse(parkingLot, carSlot);
	}

	@Override
	public ParkingLotResponse getStatus(ParkingLot parkingLot) {
		if (isInValid(parkingLot, Constants.DUMMY)) {
			return new ParkingLotResponse(parkingLot, "Input data is invalid", false);
		}
		Map<Integer, ParkingSlot> slotMap = parkingLot.getSlotsMap();
		StringBuffer sb;
		if (slotMap.size() <= 0) {
			return new ParkingLotResponse(parkingLot, "Parkinglot is empty", false);
		} else {
			sb = new StringBuffer(String.format("%s %25s %27s", "Slot No", "Registration No", "Colour"));
			for (Map.Entry<Integer, ParkingSlot> parkingSlots : slotMap.entrySet()) {
				sb.append(String.format("\n%s %29s %29s", parkingSlots.getKey(), parkingSlots.getValue().getCarNumber(),
						parkingSlots.getValue().getCarColor()));
			}
		}

		return sendResponse(parkingLot, sb.toString());

	}

	private ParkingLotResponse sendResponse(ParkingLot parkingLot, String responseString) {
		Boolean resonseFlag = true;
		String responseMessage = responseString;
		if (responseString.isEmpty()) {
			resonseFlag = false;
			responseMessage = "Not found";
		}
		return new ParkingLotResponse(parkingLot, responseMessage, resonseFlag);
	}

	private boolean isInValid(ParkingLot parkingLot, String data) {
		if (Objects.isNull(parkingLot) || Objects.isNull(data)) {
			return true;
		}
		return false;
	}
}
