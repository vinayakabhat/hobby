package com.gojek.assignment.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

//This class contain whole parkingLot information
public class ParkingLot {

	// size of the parking lot
	private Integer size;

	// holds available free slots in
	private Queue<Integer> freeSlotsAvailable;

	// holds information about each slot in sorted way
	private Map<Integer, ParkingSlot> slotsMap;

	//creates a parking lot with given size
	public ParkingLot(int size) {
		this.size = size;
		this.freeSlotsAvailable = new PriorityQueue<Integer>();
		this.slotsMap = new LinkedHashMap<Integer, ParkingSlot>();
		IntStream.range(1, size+1).forEach(this.freeSlotsAvailable::add);
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Queue<Integer> getFreeSlotsAvailable() {
		return freeSlotsAvailable;
	}

	public void setFreeSlotsAvailable(Queue<Integer> freeSlotsAvailable) {
		this.freeSlotsAvailable = freeSlotsAvailable;
	}

	public Map<Integer, ParkingSlot> getSlotsMap() {
		return slotsMap;
	}

	public void setSlotsMap(Map<Integer, ParkingSlot> slotsMap) {
		this.slotsMap = slotsMap;
	}

}
