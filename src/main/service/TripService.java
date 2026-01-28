package service;

import model.RideRequest;
import model.Driver;
import model.Trip;
import model.TripStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TripService {
    
    // Stack to store all trips (for tracking and history)
    private Stack<Trip> tripStack;
    
    // Constructor initializes the trip stack
    public TripService() {
        tripStack = new Stack<>();
    }

    /**
     * Creates a new trip, assigns the driver, and marks it as ACTIVE.
     */
    public Trip createTrip(RideRequest request, Driver driver) {

        // Create a new Trip object
        Trip trip = new Trip();

        // Assign ride request and driver
        trip.setRideRequest(request);
        trip.setDriver(driver);

        // Set trip status to ACTIVE
        trip.setStatus(TripStatus.ACTIVE);

        // Set trip start time
        trip.setStartTime(LocalDateTime.now());
        
        // Add to internal tracking stack
        tripStack.push(trip);

        // Log trip creation
        System.out.println("Trip created successfully.");
        System.out.println("Rider: " + request.getRider().getName());
        System.out.println("Driver: " + driver.getId());
        System.out.println("Status: ACTIVE");
        System.out.println("Total trips tracked: " + tripStack.size());

        return trip;
    }
    
    /**
     * Gets all trips (for reporting purposes).
     * @return List of all trips
     */
    public List<Trip> getAllTrips() {
        return new ArrayList<>(tripStack);
    }
    
    /**
     * Gets the most recent trip.
     * @return The most recent trip, or null if no trips exist
     */
    public Trip getLatestTrip() {
        return tripStack.isEmpty() ? null : tripStack.peek();
    }
    
    /**
     * Gets the count of all trips.
     * @return Number of trips
     */
    public int getTripCount() {
        return tripStack.size();
    }
}

