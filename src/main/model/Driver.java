package model;

public class Driver {
    private String id;
    private String name;
    private String currentLocation;
    private boolean isAvailable;

    public Driver(String id, String name, String currentLocation) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCurrentLocation() { return currentLocation; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}