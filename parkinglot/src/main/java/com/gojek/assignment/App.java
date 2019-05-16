package com.gojek.assignment;

import com.gojek.assignment.inputprocessor.ParkingDataProcessor;
import com.gojek.assignment.objectfactory.ParkingObjectsFactory;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	//creates dependencies required for object creation in project
    	ParkingObjectsFactory.createObjectFactory();
    	//Starting point of the App
    	ParkingDataProcessor.startSystem();
    }
}
