package model;

import java.util.Stack;

public class Driver {
    private String id;
    private String name;
    private String vehicleNumber;
    private String phoneNumber;
    private boolean isAvailable;
    private Stack<Trip> tripHistory;
    private Location currentLocation;

    public Driver(String id, String name, String vehicleNumber, String phoneNumber, Location initialLocation) {
        this.id = id;
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.phoneNumber = phoneNumber;
        this.isAvailable = true;
        this.tripHistory = new Stack<>();
        this.currentLocation = initialLocation;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    // Trip history management
    public void addTripToHistory(Trip trip) {
        if (tripHistory.size() >= 1000) { // Prevent stack overflow with reasonable limit
            // Remove oldest trip (bottom of the stack)
            Stack<Trip> temp = new Stack<>();
            while (tripHistory.size() > 1) {
                temp.push(tripHistory.pop());
            }
            tripHistory.pop(); // Remove the oldest trip
            while (!temp.isEmpty()) {
                tripHistory.push(temp.pop());
            }
        }
        tripHistory.push(trip);
    }

    public Stack<Trip> getTripHistory() {
        return tripHistory;
    }

    public int getTotalTrips() {
        return tripHistory.size();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", isAvailable=" + isAvailable +
                ", totalTrips=" + getTotalTrips() +
                '}';
    }
}
