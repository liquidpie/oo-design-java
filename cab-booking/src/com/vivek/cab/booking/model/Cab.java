package com.vivek.cab.booking.model;

public class Cab {
  String id;
  String driverName;

  Trip currentTrip;
  Location currentLocation;
  Boolean isAvailable;

  public Cab(String id, String driverName) {
    this.id = id;
    this.driverName = driverName;
    this.isAvailable = true;
  }

  public String getId() {
    return id;
  }

  public String getDriverName() {
    return driverName;
  }

  public Trip getCurrentTrip() {
    return currentTrip;
  }

  public Location getCurrentLocation() {
    return currentLocation;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setCurrentTrip(Trip currentTrip) {
    this.currentTrip = currentTrip;
  }

  public void setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
  }

  public void setIsAvailable(Boolean available) {
    isAvailable = available;
  }

  @Override
  public String toString() {
    return "Cab{" +
        "id='" + id + '\'' +
        ", driverName='" + driverName + '\'' +
        ", currentLocation=" + currentLocation +
        ", isAvailable=" + isAvailable +
        '}';
  }
}
