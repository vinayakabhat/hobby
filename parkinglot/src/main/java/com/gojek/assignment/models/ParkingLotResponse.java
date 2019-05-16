package com.gojek.assignment.models;

/**
 * @author Vinayaka K R It is a response having ParkingLot object and other
 *         details
 *
 */
public class ParkingLotResponse {

	private ParkingLot parkinglot;
	private String message;

	public ParkingLotResponse(ParkingLot parkinglot, String message, Boolean success) {
		super();
		this.parkinglot = parkinglot;
		this.message = message;
		this.success = success;
	}

	public ParkingLotResponse() {
		
	}

	private Boolean success;

	public ParkingLot getParkinglot() {
		return parkinglot;
	}

	public void setParkinglot(ParkingLot parkinglot) {
		this.parkinglot = parkinglot;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
