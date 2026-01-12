package service;

import model.Driver;
import model.Location;
import model.Trip;

import java.util.*;

public class DriverService {
    private final Map<String, Driver> drivers;
    private final Set<String> registeredVehicleNumbers;

    public DriverService() {
        this.drivers = new HashMap<>();
        this.registeredVehicleNumbers = new HashSet<>();
    }

    /**
     * Registers a new driver if the vehicle number is not already registered
     * @param name Driver's name
     * @param vehicleNumber Vehicle registration number
     * @param phoneNumber Driver's contact number
     * @param initialLocation Initial location of the driver
     * @return The newly created Driver object, or null if registration failed
     */
    public Driver registerDriver(String name, String vehicleNumber, String phoneNumber, Location initialLocation) {
        if (registeredVehicleNumbers.contains(vehicleNumber)) {
            System.out.println("Vehicle number " + vehicleNumber + " is already registered.");
            return null;
        }

        String driverId = "D" + (drivers.size() + 1);
        Driver newDriver = new Driver(driverId, name, vehicleNumber, phoneNumber, initialLocation);
        
        drivers.put(driverId, newDriver);
        registeredVehicleNumbers.add(vehicleNumber);
        
        System.out.println("Driver " + name + " registered successfully with ID: " + driverId);
        return newDriver;
    }

    /**
     * Updates driver's availability status
     * @param driverId ID of the driver
     * @param isAvailable New availability status
     * @return true if update was successful, false otherwise
     */
    public boolean updateDriverAvailability(String driverId, boolean isAvailable) {
        Driver driver = drivers.get(driverId);
        if (driver == null) {
            System.out.println("Driver not found with ID: " + driverId);
            return false;
        }
        
        driver.setAvailable(isAvailable);
        System.out.println("Driver " + driverId + " availability updated to: " + (isAvailable ? "Available" : "Unavailable"));
        return true;
    }

    /**
     * Adds a completed trip to the driver's history
     * @param driverId ID of the driver
     * @param trip The completed trip
     * @return true if the trip was added successfully, false otherwise
     */
    public boolean addTripToDriverHistory(String driverId, Trip trip) {
        Driver driver = drivers.get(driverId);
        if (driver == null) {
            System.out.println("Driver not found with ID: " + driverId);
            return false;
        }
        
        driver.addTripToHistory(trip);
        return true;
    }

    /**
     * Gets a driver by ID
     * @param driverId ID of the driver to retrieve
     * @return The Driver object, or null if not found
     */
    public Driver getDriver(String driverId) {
        return drivers.get(driverId);
    }

    /**
     * Gets all registered drivers
     * @return Collection of all drivers
     */
    public Collection<Driver> getAllDrivers() {
        return drivers.values();
    }

    /**
     * Gets all available drivers
     * @return List of available drivers
     */
    public List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    /**
     * Updates driver's current location
     * @param driverId ID of the driver
     * @param newLocation New location of the driver
     * @return true if update was successful, false otherwise
     */
    public boolean updateDriverLocation(String driverId, Location newLocation) {
        Driver driver = drivers.get(driverId);
        if (driver == null) {
            System.out.println("Driver not found with ID: " + driverId);
            return false;
        }
        
        driver.setCurrentLocation(newLocation);
        return true;
    }

    /**
     * Gets the total number of registered drivers
     * @return Number of drivers
     */
    public int getTotalDrivers() {
        return drivers.size();
    }

    /**
     * Gets the total number of available drivers
     * @return Number of available drivers
     */
    public int getAvailableDriversCount() {
        return getAvailableDrivers().size();
    }
}
