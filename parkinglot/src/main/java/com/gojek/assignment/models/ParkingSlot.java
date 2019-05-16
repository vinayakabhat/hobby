package com.gojek.assignment.models;

public class ParkingSlot {

	public ParkingSlot(Integer slotId, String carNumber, String carColor) {
		super();
		this.slotId = slotId;
		this.carNumber = carNumber;
		this.carColor = carColor;
	}

	public ParkingSlot() {
		
	}

	//holds unique id of the slot
	private Integer slotId;
	
	//holds unique registration id of the car
	private String carNumber;

	//holds car color
	private String carColor;
	

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}


	
}
