package model;

import util.IdGenerator;
import java.time.LocalDateTime;

public class Trip {
    private String tripId;
    private Driver driver;
    private RideRequest request;
    private TripStatus status;
    private LocalDateTime startTime;

    public Trip() {
        // Default constructor for stubs
        this.tripId = IdGenerator.generateTripId();
    }

    public Trip(Driver driver, RideRequest request) {
        this.tripId = IdGenerator.generateTripId();
        this.driver = driver;
        this.request = request;
        this.status = TripStatus.DISPATCHED;
    }

    public String getTripId() { return tripId; }
    public Driver getDriver() { return driver; }
    public RideRequest getRequest() { return request; }
    public TripStatus getStatus() { return status; }
    public LocalDateTime getStartTime() { return startTime; }
    
    public void setStatus(TripStatus status) { this.status = status; }
    public void setDriver(Driver driver) { this.driver = driver; }
    public void setRideRequest(RideRequest request) { this.request = request; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
}