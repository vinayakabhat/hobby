package com.gojek.assignment.objectfactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.gojek.assignment.models.ParkingException;

public class ParkingObjectsFactory {
	/*
	 * here a map of string and its corresponding class object is created by reading
	 * property from a file and later it is used for dependency injection
	 */

	static final Logger logger = Logger.getLogger("ParkingObjectsFactory");
	static String line = null;
	static Map<String, Object> objectMap = new HashMap<>();
	public static void createObjectFactory() throws Exception {
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader("src/main/resources/dependencyMap.properties");
			bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] properties = line.split("=");
				Class c = Class.forName(properties[1]);
				objectMap.put(properties[0], c.newInstance());
//				logger.info("dependency " + properties[0] + "=" + properties[1]);
			}
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.info("Exception " + e.getMessage());
			throw new ParkingException(e.getMessage()); 
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				logger.info("Exception " + e.getMessage());
				throw new ParkingException(e.getMessage()); 
			}
		}

	}

	public static Object getObject(String className) {
		return objectMap.get(className);
	}

}
