package service;

import model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DriverService {
    
    // Stack to store all drivers (LIFO - Last In First Out)
    private Stack<Driver> driverStack;
    
    // Constructor initializes the driver stack
    public DriverService() {
        driverStack = new Stack<>();
    }
    
    /**
     * Adds a new driver to the system.
     * @param driver The driver to add
     */
    public void addDriver(Driver driver) {
        driverStack.push(driver);
        System.out.println("Driver added: " + driver.getId() + " at location " + driver.getCurrentLocation());
    }

    /**
     * Returns a list of all drivers currently marked as AVAILABLE.
     */
    public List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        
        // Iterate through all drivers in the stack
        for (Driver driver : driverStack) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        
        System.out.println("Found " + availableDrivers.size() + " available driver(s)");
        return availableDrivers;
    }

    /**
     * Updates the status of a specific driver.
     * @param driverId The ID of the driver
     * @param available True if available, False if busy
     */
    public void updateDriverStatus(String driverId, boolean available) {
        boolean found = false;
        
        // Find the driver by ID and update their status
        for (Driver driver : driverStack) {
            if (driver.getId().equals(driverId)) {
                driver.setAvailable(available);
                found = true;
                System.out.println("Driver " + driverId + " status updated to: " + 
                    (available ? "AVAILABLE" : "BUSY"));
                break;
            }
        }
        
        if (!found) {
            System.out.println("Warning: Driver " + driverId + " not found in system");
        }
    }
    
    /**
     * Gets all drivers (for debugging/reporting purposes).
     * @return List of all drivers
     */
    public List<Driver> getAllDrivers() {
        return new ArrayList<>(driverStack);
    }
}